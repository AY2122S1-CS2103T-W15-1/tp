package classmate;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import classmate.commons.core.Config;
import classmate.commons.core.LogsCenter;
import classmate.commons.core.Version;
import classmate.commons.exceptions.DataConversionException;
import classmate.commons.util.ConfigUtil;
import classmate.commons.util.StringUtil;
import classmate.logic.Logic;
import classmate.logic.LogicManager;
import classmate.model.Classmate;
import classmate.model.Model;
import classmate.model.ModelManager;
import classmate.model.ReadOnlyClassmate;
import classmate.model.ReadOnlyUserPrefs;
import classmate.model.UserPrefs;
import classmate.model.util.SampleDataUtil;
import classmate.storage.ClassmateStorage;
import classmate.storage.JsonClassmateStorage;
import classmate.storage.JsonUserPrefsStorage;
import classmate.storage.Storage;
import classmate.storage.StorageManager;
import classmate.storage.UserPrefsStorage;
import classmate.ui.Ui;
import classmate.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Classmate ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ClassmateStorage classmateStorage = new JsonClassmateStorage(userPrefs.getClassmateFilePath());
        storage = new StorageManager(classmateStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s ClassMATE and {@code userPrefs}. <br>
     * The data from the sample ClassMATE will be used instead if {@code storage}'s ClassMATE is not found,
     * or an empty ClassMATE will be used instead if errors occur when reading {@code storage}'s ClassMATE.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyClassmate> classmateOptional;
        ReadOnlyClassmate initialData;
        try {
            classmateOptional = storage.readClassmate();
            if (!classmateOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Classmate");
            }
            initialData = classmateOptional.orElseGet(SampleDataUtil::getSampleClassmate);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Classmate");
            initialData = new Classmate();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Classmate");
            initialData = new Classmate();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Classmate");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Classmate " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ClassMATE ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
