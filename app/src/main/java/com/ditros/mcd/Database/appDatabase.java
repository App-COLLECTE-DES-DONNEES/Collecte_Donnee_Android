package com.ditros.mcd.Database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.ditros.mcd.Dao.*;
import com.ditros.mcd.model.*;
import org.jetbrains.annotations.NotNull;

@Database(version=8,exportSchema = false,entities = {AccidentBrightnessCondition.class,
        AccidentClimaticCondition.class, AccidentImpactType.class,
        AccidentSeverity.class, AccidentType.class,Municipality.class,OccupantRestraintSystem.class,PersonAction.class,
PersonGender.class,PersonRoadType.class,PersonTraumaSeverity.class,RoadBlock.class,RoadCategory.class,Road.class,
RoadIntersection.class,RoadSlopSection.class,RoadState.class,RoadTrafficControl.class,RoadType.class,
VehicleModel.class,VehicleSpecialFunction.class,VehicleType.class,City.class,Country.class,Accident.class,VehicleBrand.class,Virage.class,WearingHelmet.class,Department.class,
PersonDrugUse.class,AlcoholTestStatus.class,AlcoholTestType.class,AlcoholTestResult.class,PersonAlcoholConsumption.class,VehicleAction.class,
SeatingPlace.class,SeatingRange.class})
public abstract class  appDatabase extends RoomDatabase {

    private static final String DB_Name="mcd_db";
    private static volatile  appDatabase instance;

    public abstract AccidentClimaticConditionDAO accidentClimaticConditionDAO();
    public abstract AccidentBrightnessConditionDAO accidentBrightnessConditionDAO();
    public abstract AccidentDao accidentDao();
    public abstract AccidentImpactTypeDAO accidentImpactTypeDAO();
    public abstract VehicleActionDAO vehicleActionDAO();
    public abstract AccidentTypeDAO accidentTypeDAO();
    public abstract AccidentSeverityDAO accidentSeverityDAO();
    public abstract MunicipalityDAO municipalityDAO();
    public abstract OccupantRestraintSystemDAO occupantRestraintSystemDAO();
    public abstract AlcoholTestTypeDAO alcoholTestTypeDAO();
    public abstract AlcoholTestStatusDAO alcoholTestStatusDAO();
    public abstract AlcoholTestResultDAO alcoholTestResultDAO();
    public abstract PersonAlcoholConsumptionDAO personAlcoholConsumptionDAO();

    public abstract PersonActionDAO personActionDAO();
    public abstract PersonGenderDAO personGenderDAO();
    public abstract PersonRoadTypeDAO personRoadTypeDAO();

    public abstract PersonDrugUseDAO personDrugUseDAO();

    public abstract PersonTraumaSeverityDAO personTraumaSeverityDAO();
    public abstract RoadBlockDAO roadBlockDAO();
    public abstract RoadCategoryDAO roadCategoryDAO();
    public abstract RoadDAO roadDAO();
    public abstract RoadIntersectionDAO roadIntersectionDAO();
    public abstract RoadSlopSectionDAO roadSlopSectionDAO();
    public abstract RoadStateDAO roadStateDAO();
    public abstract RoadTrafficControlDAO roadTrafficControlDAO();
    public abstract RoadTypeDAO roadTypeDAO();
    public abstract VehicleModelDAO vehicleModelDAO();
    public abstract VehiculeBrandDAO vehiculeBrandDAO();
    public abstract VirageDAO virageDAO();
    public abstract SeatingPlaceDAO seatingPlaceDAO();

    public abstract CityDAO cityDAO();
    public abstract SeatingRangeDAO seatingRangeDAO();
    public abstract WearingHelmetDAO wearingHelmetDAO();
    public abstract VehicleSpecialFunctionDAO vehicleSpecialFunctionDAO();
    public abstract VehicleTypeDAO vehicleTypeDAO();


    public static appDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,  appDatabase.class, DB_Name).allowMainThreadQueries().
                    fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    @NonNull
    @NotNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @NotNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
