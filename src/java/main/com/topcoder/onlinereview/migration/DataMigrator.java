/**
 * Copyright (C) 2005 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.onlinereview.migration;

import com.topcoder.db.connectionfactory.ConfigurationException;
import com.topcoder.db.connectionfactory.DBConnectionFactory;
import com.topcoder.db.connectionfactory.DBConnectionFactoryImpl;
import com.topcoder.db.connectionfactory.UnknownConnectionException;
import com.topcoder.onlinereview.migration.dto.newschema.project.ProjectNew;
import com.topcoder.onlinereview.migration.dto.oldschema.ProjectOld;
import com.topcoder.onlinereview.migration.persistence.ProjectPersistence;
import com.topcoder.onlinereview.migration.persistence.ScorecardPersistence;
import com.topcoder.util.config.ConfigManager;
import com.topcoder.util.idgenerator.IDGenerationException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;
import java.util.List;


/**
 * The DataMigrator.
 *
 * @author brain_cn
 * @version 1.0
 */
public class DataMigrator {
	public static final String NAMESPACE = DataMigrator.class.getName();
    private Connection loaderConn = null;
    private Connection persistConn = null;
    private ScorecardLoader scorecardLoader = null;
    private ScorecardPersistence scorecardPersistence = null;
    private ProjectLoader projectLoader = null;
    private ProjectPersistence projectPersistence = null;
    private ScorecardTransformer scorecardTransformer = null;
    private ProjectTransformer projectTransformer = null;

    /**
     * Creates a new DataMigrator object.
     *
     * @param loaderConn the connection to load data
     * @param persistConn the connection to persist
     *
     * @throws Exception if error occurs while load or store data
     */
    public DataMigrator(Connection loaderConn, Connection persistConn)
        throws Exception {
		Util.info("Create DataMigrator with two connection");
    	init(loaderConn, persistConn);
    }

    private void init(Connection loaderConn, Connection persistConn)
    throws Exception {
        this.loaderConn = loaderConn;
        this.persistConn = persistConn;

        // Prepare transformers
        this.scorecardTransformer = new ScorecardTransformer();
        this.projectTransformer = new ProjectTransformer();

        // Prepare loaders
        this.projectLoader = new ProjectLoader(loaderConn);
        this.scorecardLoader = new ScorecardLoader(loaderConn);

        // Prepare persistences
        this.scorecardPersistence = new ScorecardPersistence(persistConn);
        this.projectPersistence = new ProjectPersistence(persistConn);    	
    }

    public DataMigrator() throws Exception {
		Util.info("Create DataMigrator that load connection from config file");
    	String loaderConnName = getString("loader_conn_name", "tcs_catalog_old");
    	String persistConnName = getString("persist_conn_name", "tcs_catalog_new");
    	DBConnectionFactory dbf = getDBConnectionFactory();
    	init(dbf.createConnection(loaderConnName), dbf.createConnection(persistConnName));
    }

    private String getString(String propertyName, String defaultValue) throws Exception {
    	ConfigManager cm = ConfigManager.getInstance();
    	String value = cm.getString(NAMESPACE, propertyName);
    	
    	if (value == null || value.trim().length() == 0) {
    		Util.info(propertyName + " not set, use default " + defaultValue);
    		return defaultValue;
    	} else {
    		Util.info(propertyName + " is set, the value is " + value);
    		return value;
    	}
    }

    /**
     * Return DBConnectionFactory to create connection.
     *
     * @return DBConnectionFactory
     *
     * @throws UnknownConnectionException to JUnit
     * @throws ConfigurationException to JUnit
     */
    private DBConnectionFactory getDBConnectionFactory()
        throws UnknownConnectionException, ConfigurationException {
        return new DBConnectionFactoryImpl(DBConnectionFactoryImpl.class.getName());
    }
    
    /**
     * Migrate old online review data to new schema.
     *
     * @throws Exception if error occurs while load or store data
     */
    public void migrate() throws Exception {
		Util.info("start migrate");
        migrateScorecard();
        migrateProject();
		Util.info("end migrate");
    }

    /**
     * Release all resource.
     */
    public void close() {
		Util.info("close connections");
        DatabaseUtils.closeSilently(loaderConn);
        DatabaseUtils.closeSilently(persistConn);
    }

    /**
     * Migrate scorecard data.
     *
     * @throws Exception if error occurs while load or store data
     */
    public void migrateScorecard() throws Exception {
		Util.start("migrateScorecard");
        // Load data
        List input = scorecardLoader.loadScorecardTemplate();

        // transform data
        List output = scorecardTransformer.transformScorecardTemplate(input);

        // store data
        scorecardPersistence.storeScorecard(output);
		Util.logAction(input.size(), "migrateScorecard");
    }

    /**
     * Migrate Project/ProjectAudit data.
     *
     * @throws IDGenerationException if error occurs while generate id
     * @throws SQLException if error occurs while load or store data
     */
    public void migrateProject() throws Exception {
		Util.start("migrateProject");
        // Load all project ids
        List input = projectLoader.loadProjectIds();
        
        // Remove migrated project
        input.removeAll(MapUtil.getMigratedProjectIds());
        
        for (Iterator iter = input.iterator(); iter.hasNext();) {
        	int projectId = Integer.parseInt(iter.next().toString());
        	try {
	        	ProjectOld oldProject = projectLoader.loadProject(projectId);
	        	ProjectNew newProject = projectTransformer.transformProject(oldProject);
	        	projectPersistence.storeProject(newProject);
	        	MapUtil.storeMigratedProjectId(oldProject.getProjectId(), newProject.getProjectId());
        	} catch(Exception e) {
        		Util.warn("Failed to migrate project, projectId: " + projectId);
        	}
        }

		Util.logAction(input.size(), "migrateProject");
    }

    /**
     * Returns the projectLoader.
     *
     * @return Returns the projectLoader.
     */
    public ProjectLoader getProjectLoader() {
        return projectLoader;
    }

    /**
     * Set the projectLoader.
     *
     * @param projectLoader The projectLoader to set.
     */
    public void setProjectLoader(ProjectLoader projectLoader) {
        this.projectLoader = projectLoader;
    }

    /**
     * Returns the projectPersistence.
     *
     * @return Returns the projectPersistence.
     */
    public ProjectPersistence getProjectPersistence() {
        return projectPersistence;
    }

    /**
     * Set the projectPersistence.
     *
     * @param projectPersistence The projectPersistence to set.
     */
    public void setProjectPersistence(ProjectPersistence projectPersistence) {
        this.projectPersistence = projectPersistence;
    }

    /**
     * Returns the scorecardLoader.
     *
     * @return Returns the scorecardLoader.
     */
    public ScorecardLoader getScorecardLoader() {
        return scorecardLoader;
    }

    /**
     * Set the scorecardLoader.
     *
     * @param scorecardLoader The scorecardLoader to set.
     */
    public void setScorecardLoader(ScorecardLoader scorecardLoader) {
        this.scorecardLoader = scorecardLoader;
    }

    /**
     * Returns the scorecardPersistence.
     *
     * @return Returns the scorecardPersistence.
     */
    public ScorecardPersistence getScorecardPersistence() {
        return scorecardPersistence;
    }

    /**
     * Set the scorecardPersistence.
     *
     * @param scorecardPersistence The scorecardPersistence to set.
     */
    public void setScorecardPersistence(ScorecardPersistence scorecardPersistence) {
        this.scorecardPersistence = scorecardPersistence;
    }

    /**
     * Returns the projectTransformer.
     *
     * @return Returns the projectTransformer.
     */
    public ProjectTransformer getProjectTransformer() {
        return projectTransformer;
    }

    /**
     * Set the projectTransformer.
     *
     * @param projectTransformer The projectTransformer to set.
     */
    public void setProjectTransformer(ProjectTransformer projectTransformer) {
        this.projectTransformer = projectTransformer;
    }

    /**
     * Returns the scorecardTransformer.
     *
     * @return Returns the scorecardTransformer.
     */
    public ScorecardTransformer getScorecardTransformer() {
        return scorecardTransformer;
    }

    /**
     * Set the scorecardTransformer.
     *
     * @param scorecardTransformer The scorecardTransformer to set.
     */
    public void setScorecardTransformer(ScorecardTransformer scorecardTransformer) {
        this.scorecardTransformer = scorecardTransformer;
    }
}
