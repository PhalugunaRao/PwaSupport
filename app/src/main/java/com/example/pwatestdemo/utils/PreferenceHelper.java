package com.example.pwatestdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

public class PreferenceHelper {
    static final String TAG = "RegisterGcmIdInfo";
    private static final String PREF_IS_HRA_COMPLETED = "isHraCompleted";
    private static final String PREF_ORDER_MEDS = "isOrderMeds";
    private static final String PREF_ENCRYTION = "isEncryption";
    private static final String PREF_FIRST_REGISTER = "isFirstRegister";
    private static final String PREF_IS_FIT_CONNECTED = "isFitConnected";
    private static final String PREF_IS_HEALTH_CONNECTED = "isHealthConnected";
    private static final String PREF_UPDATE_APP_LATER = "updateLater";
    private static final String PACKAGE_LIST = "packages";
    private static final String DIALOG_PACKAGE_LIST = "dialog_package_list";
    private static final String IS_DIALOG_VIEW = "isDialogView";
    private static final String PREF_LOGINFROM_FLAG = "fromLogin";
    private static final String PREF_ADDRESS_FLAG = "selectedAddress";
    private static final String PREF_FIT_DATE_FLAG = "fitDate";
    private static final String PREF_FAMILYDOC = "isFamilyDoc";
    private static final String PREF_FD_CHIEF_COMPLAINT = "fd_chief_complaint";
    private static final String PREF_LAST_COMPLETED_CONSULTATION_ID = "LastCompletedConsultationId";
    private static final String PREFS_NAME = "material_showcaseview_prefs";
    private static final String STATUS = "status_";
    /*
    showcase
     */
    public static int SEQUENCE_NEVER_STARTED = 0;
    public static int SEQUENCE_FINISHED = -1;
    private final SharedPreferences mPrefs;
    private final String PREF_CheckManual = "ManualData";
    private final String PREF_APKVersion = "apkVersion";
    private final String PREF_SkipMobileNumber = "skipMobileNumber";
    private final String PREF_IsLogin = "IsLogin";
    private final String PREF_IsCheckLastStatus = "IsLastStatus";
    private final String PREF_AppToken = "AppToken";
    private final String PREF_UserId = "UserId";
    private final String PREF_FIRST_USER_WIZARD = "IsWizardCompliteFirst";
    private final String PREF_Lat = "Lat";
    private final String PREF_Long = "Long";
    private final String PREF_UserName = "UserName";
    private final String PREF_Password = "Password";
    private final String PREF_Device_Password = "Device_Password";
    private final String PREF_Device_Name = "Device_Name";
    private final String PREF_X_EKINCARE_KEY = "EkinCareKey";

    private final String DEVICE_ID = "Device_id";
    private final String PREF_X_CUSTOMER_KEY = "CustomerKey";
    private final String PREF_ProfileModel = "ProfileModel";
    private final String PREF_ProfileData = "ProfileData";
    private final String PREF_Immunization = "Immunization";
    private final String PREF_FamilyMemCount = "FamilyMemCount";
    private final String PREF_LastQuestion = "LastQuestion";
    private final String PREF_IsFamilyWizrdComplete = "IsFamilyWizrdComplete";
    private final String PREF_IsLoginVisible = "IsLoginVisible";
    private final String PREF_IsHaveCodeVisible = "IsHaveCodeVisible";
    private final String PREF_IsYou = "IsYou";
    private final String PREF_Target = "Target";
    private final String PREF_BloodGroup = "BloodGroup";
    private final String PREF_HydrocareIntakesTimeStamp = "HydrocareIntakesTimeStamp";
    private final String PREF_TemperatureTimeStamp = "TemperatureTimeStamp";
    private final String PREF_Temperature = "Temperature";
    private final String PREF_isHydrocareSubscriptionEnable = "isHydrocareSubscriptionEnable";
    private final String PREF_isBloodSOSSubscriptionEnable = "isBloodSOSSubscriptionEnable";
    private final String PREF_YouCustomer = "YouCustomer";
    private final String PREF_FamilyMemberList = "FamilyMemberList";
    private final String PREF_HydrocareSubscripted = "HydrocareSubscripted";
    private final String PREF_StepsCount = "StepsCount";
    private final String PREF_StepsTodayDate = "StepsTodaydate";
    private final String PREF_CaloriesCount = "CaloriesCount";
    private final String PREF_ABLYTOKEN = "AblyToken";
    private final String PREF_PACKAGETYPE = "PackageType";
    private final String PREF_PACKAGEID = "PackageID";
    private final String PREF_MENTAL_WELLNESS_PACKAGEID = "PREF_MENTAL_WELLNESS_PACKAGEID";
    private final String PREF_PROVIDER_NAME = "ProviderName";
    private final String PREF_PROVIDER_ID = "ProviderID";
    private final String PREF_MENTAL_WELLNESS_PROVIDER_ID = "PREF_MENTAL_WELLNESS_PROVIDER_ID";
    private final String PREF_FIRSTAPPOINTMENT = "FirstAppointment";
    private final String PREF_APPOINTMENT_TIME_DATE = "FirstDate";
    private final String PREF_LOCATION_LAT = "Locationlatitude";
    private final String PREF_LOCATION_LAN = "Locationlongitude";
    private final String PREF_SAVE_LOC_NAME = "SaveGoogleLocAddress";
    private final String PREF_TRENDS = "TrendsId";
    private final String PREF_INSTALL_TIME = "InstallTime";
    private final String PREF_UPDATE_WATER = "updateWater";
    private final String PREF_UPDATE_WATER_TOTAL = "totalUpdateWater";
    private final String PREF_RATING_FOR_COUNT = "CountsPerDay";
    private final String PREF_RATING_FOR_DAY = "CountOfDay";
    private final String PREF_IS_RATED = "IsRated";
    private final String PREF_ATE_ON_TIME = "ateOnTime";
    private final String PREF_SMOKED_TODAY = "smokedToday";
    private final String PREF_DRINKED_ALCOHOL = "drinkedAlcohol";
    private final String PREF_DRINKED_COFFEE = "drinkedCoffee";
    private final String PREF_SLEEP_ON_TIME = "sleepOnTime";
    private final String PREF_HAD_BREAKFAST = "hadbreakfast";
    private final String PREF_SKIPPED_MEAL = "skippedMeals";
    private final String PREF_HAD_GOODSLEEP = "hadGoodSleep";
    private final String PREF_TODAY_HOW_FEEL = "howWasDay";
    private final String PREF_SLEEP_TIME = "sleepTime";
    private final String PREF_WOKE_TIME = "wakeTime";
    private final String PREF_IMAGE_URL = "imageUrl";
    private final String PREF_CUSTOMER_PROFILE_IMAGE_COLOR = "imageColor";
    private final String PREF_CUSTOMER_TOKEN = "customerNToken";
    private final String PREF_IS_ANY_DIGITIZED_RECORED = "isAnyDigitizedRecords";
    private final String PREF_HAS_SEEN_DIGITIZED_RECORED = "hasSeenDigitizedRecords";
    private final String PREF_HAS_HOW_WAS_YOUR_DAY_DATA_FILLED = "showHowWasYourDay";
    private final String FAMILY_MEMBER_LIST = "totalFamilyMemberList";
    private final String PREF_TAKEN_MORNING_MEDICATION_ID = "medicationMorningId";
    private final String PREF_TAKEN_EVENING_MEDICATION_ID = "medicationEveningId";
    private final String PREF_TAKEN_AFTERNOON_MEDICATION_ID = "medicationAfternoonId";
    private final String PREF_HAS_SEEN_TUTORIAL = "hasSeenTutorial";
    private final String PREF_HAS_SEEN_ACTIVITY_FAMILY_MEMBER_TUTORIAL = "hasSeenActivityFamilyMemberTutorial";
    private final String PREF_HAS_SEEN_DOC_FAMILY_MEMBER_TUTORIAL = "hasSeenDOCFamilyMemberTutorial";
    private final String PREF_HAS_SEEN_MEDICICATION_FAMILY_MEMBER_TUTORIAL = "hasSeenMedicationFamilyMemberTutorial";
    private final String PREF_HAS_SEEN_ALLERGY_FAMILY_MEMBER_TUTORIAL = "hasSeenAllergyFamilyMemberTutorial";
    private final String PREF_HAS_SEEN_HISTORY_FAMILY_MEMBER_TUTORIAL = "hasSeenHistoryFamilyMemberTutorial";
    private final String PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL = "hasSeenDocumentUploadTutorial";
    private final String PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL = "hasSeenDocumentUploadTutorial";
    private final String PREF_RUNTIME_PERMISSION = "runtimePermission";
    private final String PREF_DOC_MAIN = "MAIN_DOC";
    private final String PREF_IS_FIRST_TIME_CHAT = "isFirstTimeChat";
    private final String PREF_IS_NP = "isNp";
    private final String PREF_IS_FIRST_TIME_DOCTORCHAT = "isFirstTimeDoctorChat";
    private final String PREF_IS_FIRST_WELLCOME = "isFirstTimeWellcome";
    private final String PREF_IS_FIRST_TIMER = "isFirstTimeTimer";
    private final String PREF_IS_FIRST_TIMER_BADGE = "isFirstTimeTimerBadge";
    private final String PREF_DOCTORID = "DoctorID";
    private final String PREF_DOCTORCHATID = "DoctorChatID";
    private final String PREF_IsFirstTimeRegister = "IsFirstTimeRegister";
    private final String PREF_LOGGED_IN_CUSTOMER_DOB = "loggedInCustomerDob";
    private final String PREF_LOGGED_IN_CUSTOMER_GENDER = "loggedInCustomerGender";
    private final String PREF_LOGGED_IN_CUSTOMER_WIZARD = "loggedInCustomerWizard";
    private final String PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER = "loggedInCustomerMobileNumber";
    private final String PREF_IsAttachImageDoctor = "IsDoctorUploadImage";
    private final String PREF_IsAblyInitFirst = "IsAblyFirst";
    private final String PREF_IsDoctorPayment = "IsDoctorPayment";
    private final String PREF_HOW_WAS_DONE = "howWasDayDone";
    private final String PREF_HOW_WAS_DAY_REMINDER = "howWasDayReminder";
    private final String PREF_CHAT_FLAG = "isChatWithSoctor";
    private final String PREF_BOOK_HEALTH_FLAG = "isBookHealthCheckUp";
    private final String PREF_AddFamilyMember_FLAG = "isAddFamilyMember";
    private final String PREF_ORDER_MEDICINE_FLAG = "isOrderMedicine";
    private final String PREF_WALLET_FLAG = "isWallet";
    private final String PREF_HEALTH_COACH_FLAG = "isHealthCoach";
    private final String PREF_DOB_FLAG = "isDobEnabled";
    private final String PREF_SOCIAL_CHALLENGE_FLAG = "isSocialChallenges";
    private final String PREF_GYM_FLAG = "isGymEnable";
    private final String PREF_LEADERBOARD_FLAG = "isLeaderboard";
    private final String PREF_GroupOne = "GROUPONE";
    private final String PREF_GroupTwo = "GroupTwo";
    private final String PREF_STEPATHON_FLAG = "isStepathonActive";
    private final String PREF_DOCINTERMEDIATE = "isINTERMEDIATE";
    private final String PREF_ANUALCHECK = "isANUALCHECK";
    private final String PREF_CONTACTHIDE = "isCONTACTHIDE";
    private final String PREF_EWAPCHECK = "isEWAPCHECK";
    private final String PREF_SPECIALISTCHECK = "isSPECIALISTCHECK";
    private final String PREF_INCLINICCHECK = "isINCLINICCHECK";
    private final String PREF_BIOMETRIC_FLAG = "isBiometricEnabled";
    private final String RATING_START_DATE = "startDate";
    private final String PREF_Whatsapp_Communication = "enabled";
    private final String PREF_Progress_Call_Count = "ProgessCallCount";
    private final String PREF_SELECTED_COLLECTION_TYPE = "pref_selected_collection_type";
    private final String PREF_SELECTED_PACKAGE_SUB_TYPE = "pref_selected_package_sub_type";
    private final String PREF_SAVE_PINCODE = "SavePinCode";
    private final String PREF_REWARDCHECK = "isREWARDCHECK";
    private final String PREF_CUSTOMER_COVID_VACCINE_INFO = "CustomerCovidVaccineInfo";
    private final String PREF_REFERRALCHECK = "isREFERRALCHECK";
    private final String PREF_VACCINATION = "isVaccination";
    private final String LOC_PERMISSION_DENIED_COUNT = "LocationPermissionDeniedCount";
    private final String KEY_TRACKED_EVENTS = "key_tracked_events";
    private final String PREF_SMARTCOVER_TYPE = "isSmartCoverType";
    private final String PREF_IsFamilyPreview = "isFamilyDoctorShown";
    private final String PREF_NOToolTip = "noTooltip";
    private final String PREF_IsFamilyDocConsulted = "isFamilyDocConsulted";
    private final String PREF_IsNoDocAssignedPrompt = "isNoDocAssignedPrompt";
    private final String PREF_ChatActive = "chat_active";
    private final String PREF_CHECKID = "";
    private final String COVID_ID = "covidId";
    private final String COVIDSTATUS = "covidStatus";
    private final String AAROGYAFIRST = "aarogyaStatus";
    private final String AAROGYA = "aarogya";
    private final String PASSWORDENABLEFIRST = "passwordStatus";
    private final String PREF_MAIN_TYPE = "mainType";
    private final String PREF_SUB_TYPE = "subType";
    private final String PREF_MAIN_TYPE_VALUE = "mainTypeValue";
    private final String PREF_SUB_TYPE_VALUE = "subTypeValue";
    private final String PREF_NORMAL_HEALTH_CHECK = "normalHealthCheck";
    private final String PREF_PREV_CONSULTATION_ID = "previousConsultationId";
    private final String PREF_Is_Connected_To_Doc = "connectToDoc";
    private final String PREF_LAST_TIMER_TIME = "LastTimerTime";
    private final String PREF_LAST_TIMER_PROGRESS = "LastTimerProgress";
    private final String PREF_COWIN_REGISTERED = "CowinRegistered";
    private final String PREF_REFERRAL_LINK = "referralLink";
    private final String PREF_REFERRAL_IMAGE_LINK = "referralImageLink";
    private final String PREF_REFERRAL_CONTENT = "referralcontent";
    private final String PREF_REFERRAL_SUBJECT = "referralsubject";
    private final String PREF_APP_UPDATE_SKIP = "UpdateSkip";
    private final String PREF_APP_UPDATE_NOW = "UpdateNow";
    private final String PREF_ISFEEDBAK = "IsFeedback";
    private final String PREF_ISUPDATESHOW = "IsUpdateShow";
    private final String PREF_AUTO_SYNC = "autoSync";
    private final String PREF_CITY_NAME = "cityValue";
    private final String PREF_STEPATHON_TEAM_FLAG = "isStepathonTeamActive";
    private final String PREF_TEAMS_FILTER = "teamsFilter";
    private final String PREF_TEAMS_PROFILE_FILTER = "profileFilter";

    private final String PREF_CALORIES_FILTER = "caloriesFilter";
    private final String PREF_STEPS_FILTER = "stepsFilter";
    private String showcaseID = null;
    private Context context;


    public PreferenceHelper(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        this.context = context;
    }

    public PreferenceHelper(Context context, String showcaseID) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        this.context = context;
        this.showcaseID = showcaseID;
    }

    public static void resetShowcase(Context context, String showcaseID) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED).apply();
    }

    public static void resetAll(Context context) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().clear().apply();
    }

    public String getNORMAL_HEALTH_CHECK() {
        return mPrefs.getString(PREF_NORMAL_HEALTH_CHECK, "");
    }

    public void setNORMAL_HEALTH_CHECK(String normal_health_check) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_NORMAL_HEALTH_CHECK, normal_health_check);
        mEditor.commit();
    }

    public Boolean getPREF_AAROGYA() {
        boolean str = mPrefs.getBoolean(AAROGYA, false);
        return str;

    }

    public void setPREF_AAROGYA(Boolean aarogya) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(AAROGYA, aarogya);
        mEditor.commit();
    }

    public String getCityKey() {
        String str = mPrefs.getString(PREF_CITY_NAME, "");
        return str;
    }

    public void setCityKey(String pref_city_name) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CITY_NAME, pref_city_name);
        mEditor.commit();
    }

    public String getSubTypeKey() {
        String str = mPrefs.getString(PREF_SUB_TYPE_VALUE, "");
        return str;
    }

    public void setSubTypeKey(String pREF_EkinKey) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SUB_TYPE_VALUE, pREF_EkinKey);
        mEditor.commit();
    }

    public String getReferralSubject() {
        String str = mPrefs.getString(PREF_REFERRAL_SUBJECT, "");
        return str;
    }

    public void setReferralSubject(String pref_referral_subject) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_REFERRAL_SUBJECT, pref_referral_subject);
        mEditor.commit();
    }

    public String getReferralContent() {
        String str = mPrefs.getString(PREF_REFERRAL_CONTENT, "");
        return str;
    }

    public void setReferralContent(String pref_referral_content) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_REFERRAL_CONTENT, pref_referral_content);
        mEditor.commit();
    }

    public String getReferralImageLink() {
        String str = mPrefs.getString(PREF_REFERRAL_IMAGE_LINK, "");
        return str;
    }

    public void setReferralImageLink(String pref_referral_image_link) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_REFERRAL_IMAGE_LINK, pref_referral_image_link);
        mEditor.commit();
    }

    public String getReferralLink() {
        String str = mPrefs.getString(PREF_REFERRAL_LINK, "");
        return str;
    }

    public void setReferralLink(String pref_referral_link) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_REFERRAL_LINK, pref_referral_link);
        mEditor.commit();
    }

    public String getTypeKey() {
        String str = mPrefs.getString(PREF_MAIN_TYPE_VALUE, "");
        return str;
    }

    public void setTypeKey(String pREF_EkinKey) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_MAIN_TYPE_VALUE, pREF_EkinKey);
        mEditor.commit();
    }

    public int getSubMainType() {
        return mPrefs.getInt(PREF_SUB_TYPE, 0);
    }

    public void setSubMainType(int data) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_SUB_TYPE, data);
        mEditor.commit();
    }

    public int getMainType() {
        return mPrefs.getInt(PREF_MAIN_TYPE, 0);
    }

    public void setMainType(int data) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_MAIN_TYPE, data);
        mEditor.commit();
    }

    public int getSecureStatus() {
        return mPrefs.getInt(PASSWORDENABLEFIRST, 0);
    }

    public void setSecureStatus(int data) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PASSWORDENABLEFIRST, data);
        mEditor.commit();
    }

    public int getAarogyaStatus() {
        return mPrefs.getInt(AAROGYAFIRST, 0);
    }

    public void setAarogyaStatus(int data) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(AAROGYAFIRST, data);
        mEditor.commit();
    }

    public String getCovidChallengeId() {
        String registrationId = mPrefs.getString(COVID_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }
        return registrationId;
    }

    public void setCovidChallengeId(String covidId) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(COVID_ID, covidId);
        editor.commit();
    }

    public Boolean getRewardCheck() {
        return mPrefs.getBoolean(PREF_REWARDCHECK, false);
    }

    public void setRewardCheck(Boolean pref_rewardcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_REWARDCHECK, pref_rewardcheck);
        mEditor.commit();
    }

    public Boolean getReferralCheck() {
        return mPrefs.getBoolean(PREF_REFERRALCHECK, false);
    }

    public void setReferralCheck(Boolean pref_referralcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_REFERRALCHECK, pref_referralcheck);
        mEditor.commit();
    }

    public Boolean getPREF_EWAPCHECK() {
        boolean str = mPrefs.getBoolean(PREF_EWAPCHECK, false);
        return str;
    }

    public void setPREF_EWAPCHECK(Boolean pref_ewapcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_EWAPCHECK, pref_ewapcheck);
        mEditor.commit();
    }

    public void setPREF_SPECIALISTCHECK(Boolean pref_specialistcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SPECIALISTCHECK, pref_specialistcheck);
        mEditor.commit();
    }

    public Boolean getPREF_SPECIALISTCHECK() {
        boolean str = mPrefs.getBoolean(PREF_SPECIALISTCHECK, false);
        return str;
    }

    public void setPREF_INCLINICCHECK(Boolean pref_incliniccheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_INCLINICCHECK, pref_incliniccheck);
        mEditor.commit();
    }

    public Boolean getPREF_INCLINICCHECK() {
        boolean str = mPrefs.getBoolean(PREF_INCLINICCHECK, false);
        return str;
    }

    public String getSmartCardType() {
        String registrationId = mPrefs.getString(PREF_SMARTCOVER_TYPE, "");
        return registrationId.isEmpty() ? "" : registrationId;
    }

    public void setSmartCardType(String type) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_SMARTCOVER_TYPE, type);
        editor.commit();
    }

    public String getCovidStatus() {
        String registrationId = mPrefs.getString(COVIDSTATUS, "New");
        if (registrationId.isEmpty()) {
            return "";
        }
        return registrationId;
    }

    public void setCovidStatus(String covidStatus) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(COVIDSTATUS, covidStatus);
        editor.commit();
    }

    /***
     * METHODS FOR INDIVIDUAL SHOWCASE VIEWS
     */
    public boolean hasFired() {
        int status = getSequenceStatus();
        return (status == SEQUENCE_FINISHED);
    }

    public void setFired() {
        setSequenceStatus(SEQUENCE_FINISHED);
    }

    /***
     * METHODS FOR SHOWCASE SEQUENCES
     */
    public int getSequenceStatus() {
        return context
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED);

    }

    public void setSequenceStatus(int status) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, status).apply();
    }

    public void resetShowcase() {
        resetShowcase(context, showcaseID);
    }

    public void close() {
        context = null;
    }

    public Boolean getPREF_NOToolTip() {
        boolean str = mPrefs.getBoolean(PREF_NOToolTip, false);
        return str;
    }

    public void setPREF_NOToolTip(Boolean pref_noToolTip) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_NOToolTip, pref_noToolTip);
        mEditor.commit();
    }

    public Boolean getPREF_ANUALCHECK() {
        boolean str = mPrefs.getBoolean(PREF_ANUALCHECK, false);
        return str;

    }

    public void setPREF_ANUALCHECK(Boolean pref_anualcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ANUALCHECK, pref_anualcheck);
        mEditor.commit();
    }


    public Boolean getPREF_FAMILYDOC() {
        boolean str = mPrefs.getBoolean(PREF_FAMILYDOC, false);
        return str;
    }

    public void setPREF_FAMILYDOC(Boolean pref_anualcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_FAMILYDOC, pref_anualcheck);
        mEditor.commit();
    }

    public Boolean getPREF_VACCINATION() {
        boolean str = mPrefs.getBoolean(PREF_VACCINATION, false);
        return str;
    }

    public void setPREF_VACCINATION(Boolean pref_anualcheck) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_VACCINATION, pref_anualcheck);
        mEditor.commit();
    }

    public Boolean getPREF_CONTACTHIDE() {
        boolean str = mPrefs.getBoolean(PREF_CONTACTHIDE, false);
        return str;
    }

    public void setPREF_CONTACTHIDE(Boolean pref_contacthide) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_CONTACTHIDE, pref_contacthide);
        mEditor.commit();
    }

    public String getPREF_GroupOne() {
        String str = mPrefs.getString(PREF_GroupOne, "step");
        return str;
    }

    public void setPREF_GroupOne(String pref_groupone) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_GroupOne, pref_groupone);
        mEditor.commit();
    }


    public String getDayStepsFilter() {
        String str = mPrefs.getString(PREF_STEPS_FILTER, "this week");
        return str;
    }

    public void setDayStepsFilter(String pref_steps_filter) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_STEPS_FILTER, pref_steps_filter);
        mEditor.commit();
    }


    public String getCaloriesFilter() {
        String str = mPrefs.getString(PREF_CALORIES_FILTER, "this week");
        return str;
    }

    public void setCaloriesFilter(String pref_calories_filter) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CALORIES_FILTER, pref_calories_filter);
        mEditor.commit();
    }

    public String getProfileFilter() {
        String str = mPrefs.getString(PREF_TEAMS_PROFILE_FILTER, "this week");
        return str;
    }

    public void setProfileFilter(String pref_teams_profile_filter) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TEAMS_PROFILE_FILTER, pref_teams_profile_filter);
        mEditor.commit();
    }

    public String getPREF_TEAMS_FILTER() {
        String str = mPrefs.getString(PREF_TEAMS_FILTER, "this week");
        return str;
    }

    public void setPREF_TEAMS_FILTER(String pref_teams_filter) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TEAMS_FILTER, pref_teams_filter);
        mEditor.commit();
    }

    public String getPREF_GroupTwo() {
        String str = mPrefs.getString(PREF_GroupTwo, "this week");
        return str;
    }

    public void setPREF_GroupTwo(String pref_grouptwo) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_GroupTwo, pref_grouptwo);
        mEditor.commit();
    }

    public Boolean getPREF_DOCINTERMEDIATE() {
        boolean str = mPrefs.getBoolean(PREF_DOCINTERMEDIATE, false);
        return str;
    }

    public void setPREF_DOCINTERMEDIATE(Boolean pref_docintermediate) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DOCINTERMEDIATE, pref_docintermediate);
        mEditor.commit();
    }

    public Boolean getPREF_LEADERBOARD_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_LEADERBOARD_FLAG, false);
        return str;
    }

    public void setPREF_LEADERBOARD_FLAG(Boolean pref_leaderboard_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_LEADERBOARD_FLAG, pref_leaderboard_flag);
        mEditor.commit();
    }

    public Boolean getPREF_SOCIAL_CHALLENGE_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_SOCIAL_CHALLENGE_FLAG, false);
        return str;
    }

    public void setPREF_SOCIAL_CHALLENGE_FLAG(Boolean pref_social_challenge_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SOCIAL_CHALLENGE_FLAG, pref_social_challenge_flag);
        mEditor.commit();
    }


    public Boolean getPREF_HEALTH_COACH_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_HEALTH_COACH_FLAG, false);
        return str;
    }

    public void setPREF_HEALTH_COACH_FLAG(Boolean pref_health_coach_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HEALTH_COACH_FLAG, pref_health_coach_flag);
        mEditor.commit();
    }

    public Boolean getPREF_DOB_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_DOB_FLAG, false);
        return str;
    }

    public void setPREF_DOB_FLAG(Boolean pref_dob_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DOB_FLAG, pref_dob_flag);
        mEditor.commit();
    }

    public Boolean getPREF_ORDER_MEDICINE_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_ORDER_MEDICINE_FLAG, false);
        return str;
    }

    public void setPREF_ORDER_MEDICINE_FLAG(Boolean pref_order_medicine_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ORDER_MEDICINE_FLAG, pref_order_medicine_flag);
        mEditor.commit();
    }

    public Boolean getPREF_WALLET_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_WALLET_FLAG, false);
        return str;
    }

    public void setPREF_WALLET_FLAG(Boolean pref_wallet_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_WALLET_FLAG, pref_wallet_flag);
        mEditor.commit();
    }


    public Boolean getPREF_AddFamilyMember_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_AddFamilyMember_FLAG, false);
        return str;
    }

    public void setPREF_AddFamilyMember_FLAG(Boolean pref_addfamilymember_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_AddFamilyMember_FLAG, pref_addfamilymember_flag);
        mEditor.commit();
    }


    public Boolean getPREF_BOOK_HEALTH_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_BOOK_HEALTH_FLAG, false);
        return str;
    }

    public void setPREF_BOOK_HEALTH_FLAG(Boolean pref_book_health_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_BOOK_HEALTH_FLAG, pref_book_health_flag);
        mEditor.commit();
    }


    public Boolean getPREF_GYM_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_GYM_FLAG, false);
        return str;
    }

    public void setPREF_GYM_FLAG(Boolean pref_gym_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_GYM_FLAG, pref_gym_flag);
        mEditor.commit();
    }

    public Boolean getPREF_CHAT_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_CHAT_FLAG, false);
        return str;
    }

    public void setPREF_CHAT_FLAG(Boolean pref_chat_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_CHAT_FLAG, pref_chat_flag);
        mEditor.commit();
    }

    public boolean getIsDoctorPayment() {
        boolean str = mPrefs.getBoolean(PREF_IsDoctorPayment, false);
        return str;
    }

    public void setDoctorPayment(boolean pref_isdoctorpayment) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsDoctorPayment, pref_isdoctorpayment);
        mEditor.commit();
    }

    public boolean getIsUploadImageDoctor() {
        boolean str = mPrefs.getBoolean(PREF_IsAttachImageDoctor, false);
        return str;
    }

    public void setIsUploadImageDoctor(boolean pref_isattachimagedoctor) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsAttachImageDoctor, pref_isattachimagedoctor);
        mEditor.commit();
    }



    public boolean isHealthConnected() {
        return mPrefs.getBoolean(PREF_IS_HEALTH_CONNECTED, false);
    }

    public void setIsHealthConnected(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_HEALTH_CONNECTED, b);
        mEditor.commit();
    }

    public boolean isFitConnected() {
        return mPrefs.getBoolean(PREF_IS_FIT_CONNECTED, false);
    }

    public void setIsFitConnected(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIT_CONNECTED, b);
        mEditor.commit();
    }


    public boolean getIsFirstTimeRegister() {
        boolean str = mPrefs.getBoolean(PREF_IsFirstTimeRegister, true);
        return str;
    }

    public void setIsFirstTimeRegister(boolean pref_isfirsttimeregister) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsFirstTimeRegister, pref_isfirsttimeregister);
        mEditor.commit();
    }

    public String getCustomerDOB() {
        String str = mPrefs.getString(PREF_LOGGED_IN_CUSTOMER_DOB, "");
        return str;
    }

    public void setLoggedinUserDOB(String pref_modequery) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_DOB, pref_modequery);
        mEditor.commit();
    }

    public String getCustomerGender() {
        String str = mPrefs.getString(PREF_LOGGED_IN_CUSTOMER_GENDER, "");
        return str;
    }

    public String getCustomerMobileNumber() {
        String str = mPrefs.getString(PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER, "");
        return str;
    }

    public void setLoggedinUserMobileNumber(String pref_mobilenumber) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER, pref_mobilenumber);
        mEditor.commit();
    }

    public void setLoggedinUserGender(String pref_modequery) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_GENDER, pref_modequery);
        mEditor.commit();
    }

    public int getCustomerWizardStatus() {
        return mPrefs.getInt(PREF_LOGGED_IN_CUSTOMER_WIZARD, 0);
    }

    public void setCustomerWizardStatus(int data) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_LOGGED_IN_CUSTOMER_WIZARD, data);
        mEditor.commit();
    }

    public String getPREF_DOCTORID() {
        String str = mPrefs.getString(PREF_DOCTORID, "");
        return str;
    }

    public void setPREF_DOCTORID(String pref_doctorid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_DOCTORID, pref_doctorid);
        mEditor.commit();
    }

    public void setIsFirstTimer(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIMER, flag);
        mEditor.commit();
    }

    public void setIsFirstWellcome(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_WELLCOME, flag);
        mEditor.commit();
    }

    public void setIsFirstTimeDoctorChat(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIME_DOCTORCHAT, flag);
        mEditor.commit();
    }



    public boolean getIsNp() {
        boolean bool = mPrefs.getBoolean(PREF_IS_NP, false);
        return bool;
    }

    public void setIsNp(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_NP, flag);
        mEditor.commit();
    }

    public boolean getIsFirstTimeChat() {
        boolean bool = mPrefs.getBoolean(PREF_IS_FIRST_TIME_CHAT, true);
        return bool;
    }

    public void setIsFirstTimeChat(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIME_CHAT, flag);
        mEditor.commit();
    }

    public void setIsMainDoc(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DOC_MAIN, flag);
        mEditor.commit();
    }

    public boolean getIsOrderMeds() {
        boolean bool = mPrefs.getBoolean(PREF_ORDER_MEDS, false);
        return bool;
    }

    public void setIsOrderMeds(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ORDER_MEDS, flag);
        mEditor.commit();
    }

    public void setHasSeenDocUploadTuts(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getNeverShowDocUploadTuts() {
        boolean bool = mPrefs.getBoolean(PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL, false);
        return bool;
    }

    public void setNeverShowDocUploadTuts(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL, flag);
        mEditor.commit();
    }

    public String getPREF_RUNTIME_PERMISSION() {
        String str = mPrefs.getString(PREF_RUNTIME_PERMISSION, "");
        return str;

    }

    public void setPREF_RUNTIME_PERMISSION(String pref_runtime_permission) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_RUNTIME_PERMISSION, pref_runtime_permission);
        mEditor.commit();
    }

    public void setHasSeenTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_TUTORIAL, flag);
        mEditor.commit();
    }

    public void setHasSeenActivityFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_ACTIVITY_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public void setHasSeenDocumentFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_DOC_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public void setHasSeenHistoryFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_HISTORY_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public void setHasSeenMedicationFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_MEDICICATION_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public void setHasSeenAllergyFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_ALLERGY_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public void setHasHowWasYourDayDataFilled(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_HOW_WAS_YOUR_DAY_DATA_FILLED, flag);
        mEditor.commit();
    }

    public void setimageUrl(String pref_image_url) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_IMAGE_URL, pref_image_url);
        mEditor.commit();
    }

    public void setSleepTime(String time) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SLEEP_TIME, time);
        mEditor.commit();
    }

    public void setWakeTime(String time) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_WOKE_TIME, time);
        mEditor.commit();
    }

    public void sethowWasDay(String pref_today_how_feel) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TODAY_HOW_FEEL, pref_today_how_feel);
        mEditor.commit();
    }

    public void setskippedMeals(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SKIPPED_MEAL, flag);
        mEditor.commit();
    }

    public void sethadbreakfast(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAD_BREAKFAST, flag);
        mEditor.commit();
    }

    public void setLateNightPhoneUsuage(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SLEEP_ON_TIME, flag);
        mEditor.commit();
    }


    public void setDrinkedCoffeeToday(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DRINKED_COFFEE, flag);
        mEditor.commit();
    }

    public void setDrinkedAlcoholToday(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DRINKED_ALCOHOL, flag);
        mEditor.commit();
    }

    public void setSmokedToday(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SMOKED_TODAY, flag);
        mEditor.commit();
    }


    public void setAteOnTime(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ATE_ON_TIME, flag);
        mEditor.commit();
    }

    public void setIsRated(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_RATED, flag);
        mEditor.commit();
    }


    public void setUserRatingDayCount(int count) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_RATING_FOR_DAY, count);
        mEditor.commit();
    }

    public int getUserRatingCount() {
        int str = mPrefs.getInt(PREF_RATING_FOR_COUNT, 0);
        return str;
    }

    public void setUserRatingCount(int count) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_RATING_FOR_COUNT, count);
        mEditor.commit();
    }

    public void settotalUpdateWater(String pref_update_water_total) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_UPDATE_WATER_TOTAL, pref_update_water_total);
        mEditor.commit();
    }


    public String getAppUpdateNow() {
        String str = mPrefs.getString(PREF_APP_UPDATE_NOW, "");
        return str;
    }

    public void setAppUpdateNow(String pref_app_update_now) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_APP_UPDATE_NOW, pref_app_update_now);
        mEditor.commit();
    }


    public String getAppUpdateSkipTime() {
        String str = mPrefs.getString(PREF_APP_UPDATE_SKIP, "");
        return str;
    }

    public void setAppUpdateSkipTime(String pref_app_update_skip) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_APP_UPDATE_SKIP, pref_app_update_skip);
        mEditor.commit();
    }


    public String getInstallTime() {
        String str = mPrefs.getString(PREF_INSTALL_TIME, "");
        return str;
    }

    public void setInstallTime(String pref_install_time) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_INSTALL_TIME, pref_install_time);
        mEditor.commit();
    }

    public void setTrendsId(String prefs_trends_id) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TRENDS, prefs_trends_id);
        mEditor.commit();

    }

    public String getSaveGoogleLocAddress() {
        String str = mPrefs.getString(PREF_SAVE_LOC_NAME, "");
        return str;
    }

    public void setSaveGoogleLocAddress(String pref_save_loc_name) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SAVE_LOC_NAME, pref_save_loc_name);
        mEditor.commit();
    }

    public void setSaveLocationPinCode(String pinCode) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SAVE_PINCODE, pinCode);
        mEditor.apply();
    }

    public String getSavedLocationPinCode() {
        return mPrefs.getString(PREF_SAVE_PINCODE, "");
    }

    public String getLocationlongitude() {
        String str = mPrefs.getString(PREF_LOCATION_LAN, "");
        return str;
    }

    public void setLocationlongitude(String pref_location_lan) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOCATION_LAN, pref_location_lan);
        mEditor.commit();
    }

    public String getLocationlatitude() {
        String str = mPrefs.getString(PREF_LOCATION_LAT, "");
        return str;
    }

    public void setLocationlatitude(String pref_location_lat) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOCATION_LAT, pref_location_lat);
        mEditor.commit();
    }

    public String getSelectedDate() {
        String str = mPrefs.getString(PREF_APPOINTMENT_TIME_DATE, "");
        return str;

    }

    public void setSelectedDate(String prefs_first_date) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_APPOINTMENT_TIME_DATE, prefs_first_date);
        mEditor.commit();

    }

    public String getSelectedAppointment() {
        String str = mPrefs.getString(PREF_FIRSTAPPOINTMENT, "");
        return str;

    }

    public void setSelectedAppointment(String prefs_first_appointment) {

        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_FIRSTAPPOINTMENT, prefs_first_appointment);
        mEditor.commit();

    }


    public long getMentalWellnessProviderID() {
        long str = mPrefs.getLong(PREF_MENTAL_WELLNESS_PROVIDER_ID, 0);
        return str;
    }

    public void setMentalWellnessProviderID(long prefs_providerid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putLong(PREF_MENTAL_WELLNESS_PROVIDER_ID, prefs_providerid);
        mEditor.commit();
    }

    public String getProviderID() {
        String str = mPrefs.getString(PREF_PROVIDER_ID, "");
        return str;
    }

    public void setProviderID(String prefs_providerid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PROVIDER_ID, prefs_providerid);
        mEditor.commit();
    }

    public long getMentalWellnessPackageID() {
        long str = mPrefs.getLong(PREF_MENTAL_WELLNESS_PACKAGEID, 0);
        return str;
    }

    public void setMentalWellnessPackageID(long pref_packageid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putLong(PREF_MENTAL_WELLNESS_PACKAGEID, pref_packageid);
        mEditor.commit();
    }


    public String getProviderName() {
        String str = mPrefs.getString(PREF_PROVIDER_NAME, "");
        return str;
    }

    public void setProviderName(String providerName) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PROVIDER_NAME, providerName);
        mEditor.commit();
    }

    public String getPackageID() {
        String str = mPrefs.getString(PREF_PACKAGEID, "");
        return str;
    }

    public void setPackageID(String pref_packageid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PACKAGEID, pref_packageid);
        mEditor.commit();
    }


    public String getAblyToken() {
        String str = mPrefs.getString(PREF_ABLYTOKEN, "");
        return str;
    }

    public void setAblyToken(String pref_ablytoken) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_ABLYTOKEN, pref_ablytoken);
        mEditor.commit();
    }

    public String getPackageType() {
        String str = mPrefs.getString(PREF_PACKAGETYPE, "");
        return str;
    }

    public void setPackageType(String pref_packagetype) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PACKAGETYPE, pref_packagetype);
        mEditor.commit();
    }


    public String getPREF_StepsTodayDate() {
        String str = mPrefs.getString(PREF_StepsTodayDate, "");
        return str;
    }

    public void setPREF_StepsTodayDate(String PREF_Stepstodaydate) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_StepsTodayDate, PREF_Stepstodaydate);
        mEditor.commit();
    }

    public String getStepsCount() {
        String str = mPrefs.getString(PREF_StepsCount, "0");
        return str;
    }

    public void setStepsCount(String pREF_StepsCount) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_StepsCount, pREF_StepsCount);
        mEditor.commit();
    }

    public String getCaloriesCount() {
        String str = mPrefs.getString(PREF_CaloriesCount, "0");
        return str;
    }

    public void setCaloriesCount(String pREF_CaloriesCount) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CaloriesCount, pREF_CaloriesCount);
        mEditor.commit();
    }


    public boolean getIsFIrstWizard() {
        boolean str = mPrefs.getBoolean(PREF_FIRST_USER_WIZARD, false);
        return str;
    }

    public void setIsFIrstWizard(boolean pPREF_FIRST_USER_WIZARD) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_FIRST_USER_WIZARD, pPREF_FIRST_USER_WIZARD);
        mEditor.commit();
    }

    public void setPREF_IsCheckLastStatus(boolean pref_ischecklaststatus) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsCheckLastStatus, pref_ischecklaststatus);
        mEditor.commit();
    }

    public boolean getIsLogin() {
        boolean str = mPrefs.getBoolean(PREF_IsLogin, false);
        return str;
    }

    public void setIsLogin(boolean pREF_IsLogin) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsLogin, pREF_IsLogin);
        mEditor.commit();
    }


    public String getAppToken() {
        String str = mPrefs.getString(PREF_AppToken, "");
        return str;
    }

    public void setAppToken(String pREF_AppToken) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_AppToken, pREF_AppToken);
        mEditor.commit();
    }

    public boolean isSentTokenFor(String regId) {

        return mPrefs.getString("token_push", "").equals(regId);

    }

    public void sentTokenFor(String regId) {

        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("token_push", regId);
        mEditor.commit();


    }

    public String getLong() {
        String str = mPrefs.getString(PREF_Long, "");
        return str;
    }

    public void setLong(String pREF_Long) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_Long, pREF_Long);
        mEditor.commit();
    }

    public String getManualData() {
        String str = mPrefs.getString(PREF_CheckManual, "");
        return str;
    }

    public void setManualData(String pref_checkmanual) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CheckManual, pref_checkmanual);
        mEditor.commit();
    }


    public String getDeviceId() {
        String str = mPrefs.getString(DEVICE_ID, "");
        return str;
    }


    public void setDeviceId(String device_id) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(DEVICE_ID, device_id);
        mEditor.commit();
    }

    public String getEkinKey() {
        String str = mPrefs.getString(PREF_X_EKINCARE_KEY, "");
        return str;
    }


    public void setEkinKey(String pREF_EkinKey) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_X_EKINCARE_KEY, pREF_EkinKey);
        mEditor.commit();
    }

    public String getCustomerKey() {
        String str = mPrefs.getString(PREF_X_CUSTOMER_KEY, "");
        return str;
    }

    public void setCustomerKey(String pREF_CustomerKey) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_X_CUSTOMER_KEY, pREF_CustomerKey);
        mEditor.commit();
    }


    public String getProfileData() {
        String str = mPrefs.getString(PREF_ProfileData, "");
        return str;
    }

    public void setProfileData(String pREF_ProfileData) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_ProfileData, pREF_ProfileData);
        mEditor.commit();
    }

    public void setYouCustomer(String pREF_youCustomer) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_YouCustomer, pREF_youCustomer);
        mEditor.commit();
    }

    public float getTarget() {
        float str = mPrefs.getFloat(PREF_Target, 0);
        return str;
    }

    public void setTarget(float pREF_target) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putFloat(PREF_Target, pREF_target);
        mEditor.commit();
    }



    public void ClearAllData() {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CITY_NAME, "");
        mEditor.putString(PREF_APP_UPDATE_NOW, "");
        mEditor.putString(PREF_APP_UPDATE_SKIP, "");
        mEditor.putBoolean(PREF_ISUPDATESHOW, false);
        mEditor.putBoolean(PREF_AUTO_SYNC, false);
        mEditor.putBoolean(PREF_ISFEEDBAK, false);
        mEditor.putBoolean(PREF_REWARDCHECK, false);
        mEditor.putBoolean(PREF_REFERRALCHECK, false);
        mEditor.putString(COVIDSTATUS, "NEW");
        mEditor.putString(PREF_UPDATE_WATER, "0");
        mEditor.putString(PREF_GroupOne, "step");
        mEditor.putString(PREF_GroupTwo, "this week");
        mEditor.putString(PREF_REFERRAL_LINK, "");
        mEditor.putString(PREF_TODAY_HOW_FEEL, "");
        mEditor.putString(PREF_UPDATE_WATER_TOTAL, "0");
        mEditor.putInt(AAROGYAFIRST, 0);
        mEditor.putInt(PASSWORDENABLEFIRST, 0);
        mEditor.putBoolean(PREF_IsLogin, false);
        mEditor.putBoolean(PREF_IS_FIRST_TIMER_BADGE, false);
        mEditor.putBoolean(PREF_BIOMETRIC_FLAG, false);
        mEditor.putString(PREF_AppToken, "");
        mEditor.putString(PREF_UserId, "");
        mEditor.putString(PREF_CHECKID, "");
        mEditor.putString(PREF_Lat, "");
        mEditor.putString(PREF_Long, "");
        mEditor.putString(PREF_UserName, "");
        mEditor.putString(PREF_Device_Password, "");
        mEditor.putString(PREF_Device_Name, "");
        mEditor.putString(PREF_X_EKINCARE_KEY, "");
        mEditor.putString(PREF_X_CUSTOMER_KEY, "");
        mEditor.putString(PREF_Password, "");
        mEditor.putString(PREF_ProfileModel, "");
        mEditor.putString(PREF_ProfileData, "");
        mEditor.putString(PREF_Immunization, "");
        mEditor.putInt(PREF_FamilyMemCount, 0);
        mEditor.putBoolean(PREF_FIRST_USER_WIZARD, false);
        mEditor.putInt(PREF_LastQuestion, 0);
        mEditor.putStringSet(PREF_IsFamilyWizrdComplete, new HashSet<String>());
        mEditor.putBoolean(PREF_IsLoginVisible, false);
        mEditor.putBoolean(PREF_IsHaveCodeVisible, false);
        mEditor.putBoolean(PREF_IsYou, true);
        mEditor.putFloat(PREF_Target, 0);
        mEditor.putString(PREF_BloodGroup, "");
        mEditor.putString(PREF_HydrocareIntakesTimeStamp, "");
        mEditor.putString(PREF_TemperatureTimeStamp, "");
        mEditor.putString(PREF_Temperature, "");
        mEditor.putBoolean(PREF_isHydrocareSubscriptionEnable, false);
        mEditor.putBoolean(PREF_isBloodSOSSubscriptionEnable, false);
        mEditor.putString(PREF_FamilyMemberList, "");
        mEditor.putString(PREF_YouCustomer, "");
        mEditor.putString(PREF_RUNTIME_PERMISSION, "");
        mEditor.putString(PREF_HydrocareSubscripted, "0");
        mEditor.putString(PREF_StepsCount, "0");
        mEditor.putString(PREF_CaloriesCount, "0");
        mEditor.putBoolean(PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL, false);
        mEditor.putBoolean(PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL, false);
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER, "");
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_GENDER, "");
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_DOB, "");
        mEditor.putString(PREF_NORMAL_HEALTH_CHECK, "");
        setStepsCount("0");
        setCaloriesCount("0");
        setAteOnTime(false);
        setDrinkedCoffeeToday(false);
        setDrinkedAlcoholToday(false);
        setSmokedToday(false);
        setLateNightPhoneUsuage(false);
        setSleepTime("0");
        setWakeTime("0");
        setSleepTime("0");
        sethadbreakfast(false);
        settotalUpdateWater("0");
        setskippedMeals(false);
        setHasHowWasYourDayDataFilled(false);
        sethowWasDay("");
        setIsOrderMeds(false);
        setPREF_CONTACTHIDE(false);
        mEditor.commit();
        setHasSeenMedicationFamilyMemeberTutorial(false);
        setHasSeenActivityFamilyMemeberTutorial(false);
        setHasSeenAllergyFamilyMemeberTutorial(false);
        setHasSeenDocumentFamilyMemeberTutorial(false);
        setHasSeenHistoryFamilyMemeberTutorial(false);
        setHasSeenTutorial(false);

        setIsFirstTimeRegister(false);
        setIsHealthConnected(false);

        setIsFitConnected(false);
        setUserRatingCount(0);
        setUserRatingDayCount(0);
        setIsRated(false);
        //setIsNp(false);
        setIsFirstTimeChat(true);
        setConnectToDoc(false);
        setTimerCallCount(0);
        setLastTimerTime(0);
        setLastTimerProgress(0);
        clearSelectedCollectionType();
        setCowinRegistered(false);
        setLocationPermissionDeniedCount(0);

    }

    public int getAPKVersion() {
        int str = mPrefs.getInt(PREF_APKVersion, 0);
        return str;
    }

    public void setAPKVersion(int APKVersion) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_APKVersion, APKVersion);
        mEditor.commit();
    }

    public void setSkipMobileNumber(boolean skipMobileNumber) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SkipMobileNumber, skipMobileNumber);
        mEditor.commit();
    }

    public boolean getPREF_SkipMobileNumber() {
        return mPrefs.getBoolean(PREF_SkipMobileNumber, false);
    }

    public boolean isEncryted() {
        return mPrefs.getBoolean(PREF_ENCRYTION, false);
    }

    public void setIsEncrypted(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ENCRYTION, b);
        mEditor.commit();
    }

    public boolean isFirstRegister() {
        return mPrefs.getBoolean(PREF_FIRST_REGISTER, false);
    }

    public void setFirstRegister(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_FIRST_REGISTER, b);
        mEditor.commit();
    }

    public void setUpdateLater(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_UPDATE_APP_LATER, b);
        mEditor.commit();
    }

    public boolean isUpdate() {
        return mPrefs.getBoolean(PREF_UPDATE_APP_LATER, false);
    }







    public boolean getDialogView() {
        return mPrefs.getBoolean(IS_DIALOG_VIEW, false);
    }

    public void setDialogView(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(IS_DIALOG_VIEW, b);
        mEditor.commit();
    }

    public Boolean isPREF_STEPATHON_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_STEPATHON_FLAG, false);
        return str;
    }

    public void setPREF_STEPATHON_FLAG(Boolean pref_chat_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_STEPATHON_FLAG, pref_chat_flag);
        mEditor.commit();
    }

    public Boolean isPREF_STEPATHON_TEAM_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_STEPATHON_TEAM_FLAG, false);
        return str;
    }

    public void setPREF_STEPATHON_TEAM_FLAG(Boolean teamStepathon) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_STEPATHON_TEAM_FLAG, teamStepathon);
        mEditor.commit();
    }


    public String getLoginFrom() {
        return mPrefs.getString(PREF_LOGINFROM_FLAG, null);
    }

    public void setLoginFrom(String s) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGINFROM_FLAG, s);
        mEditor.commit();
    }

    public String getPrefAddressFlag() {
        return mPrefs.getString(PREF_ADDRESS_FLAG, null);
    }

    public void setAddressId(String s) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_ADDRESS_FLAG, s);
        mEditor.commit();
    }

    public String getFitDate() {
        return mPrefs.getString(PREF_FIT_DATE_FLAG, null);
    }

    public void setFitDate(String formatedDate) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_FIT_DATE_FLAG, formatedDate);
        mEditor.commit();
    }

    public boolean getFeedbackData() {
        boolean str = mPrefs.getBoolean(PREF_ISFEEDBAK, false);
        return str;
    }

    public void setFeedbackData(boolean pref_isfeedbak) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ISFEEDBAK, pref_isfeedbak);
        mEditor.commit();
    }

    public boolean getUpdateShow() {
        boolean str = mPrefs.getBoolean(PREF_ISUPDATESHOW, false);
        return str;
    }

    public void setUpdateShow(boolean pref_isupdateshow) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ISUPDATESHOW, pref_isupdateshow);
        mEditor.commit();
    }

    public boolean getAutoSync() {
        boolean str = mPrefs.getBoolean(PREF_AUTO_SYNC, false);
        return str;
    }

    public void setAutoSync(boolean pref_auto_sync) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_AUTO_SYNC, pref_auto_sync);
        mEditor.commit();
    }

    public boolean getFirstTimeFamilyDoc() {
        boolean str = mPrefs.getBoolean(PREF_IsFamilyPreview, false);
        return str;
    }

    public void setFirstTimeFamilyDoc(boolean pREF_IsLogin) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsFamilyPreview, pREF_IsLogin);
        mEditor.commit();
    }

    public boolean getFamilyDocConsulted() {
        boolean str = mPrefs.getBoolean(PREF_IsFamilyDocConsulted, false);
        return str;
    }

    public void setFamilyDocConsulted(boolean isFamilyDocConsulted) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsFamilyDocConsulted, isFamilyDocConsulted);
        mEditor.commit();
    }

    public boolean getNoDocAssigned() {
        boolean str = mPrefs.getBoolean(PREF_IsNoDocAssignedPrompt, false);
        return str;
    }

    public void setNoDocAssigned(boolean isFamilyDocConsulted) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsNoDocAssignedPrompt, isFamilyDocConsulted);
        mEditor.commit();
    }

    public boolean getChatActive() {
        boolean str = mPrefs.getBoolean(PREF_ChatActive, false);
        return str;
    }

    public void setChatActive(boolean active_consult) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ChatActive, active_consult);
        mEditor.commit();
    }

    public int getPreviousConsultationId() {
        return mPrefs.getInt(PREF_PREV_CONSULTATION_ID, -1);
    }

    public void setPreviousConsultationId(int consultationId) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_PREV_CONSULTATION_ID, consultationId);
        mEditor.commit();
    }

    public String getPREF_CHECKID() {
        String id = mPrefs.getString(PREF_CHECKID, "");
        if (id.isEmpty()) {
            return "";
        }
        return id;
    }

    public void setPREF_CHECKID(String checkid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CHECKID, checkid);
        mEditor.commit();
    }

    public boolean getPREF_BIOMETRIC_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_BIOMETRIC_FLAG, false);
        return str;
    }

    public void setPREF_BIOMETRIC_FLAG(boolean isBiometricEnabled) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_BIOMETRIC_FLAG, isBiometricEnabled);
        mEditor.commit();
    }

    public String getStartDate() {
        return mPrefs.getString(RATING_START_DATE, null);
    }

    public void setStartDate(String s) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(RATING_START_DATE, s);
        mEditor.commit();
    }

    public boolean getPREF_Whatsapp_Communication() {
        return mPrefs.getBoolean(PREF_Whatsapp_Communication, false);
    }

    public void setPREF_Whatsapp_Communication(boolean is_Whatsapp_Communication) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_Whatsapp_Communication, is_Whatsapp_Communication);
        mEditor.apply();
    }


    public void setConnectToDoc(boolean isConnectedToDoc) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_Is_Connected_To_Doc, isConnectedToDoc);
        mEditor.apply();
    }

    public boolean getIsConnectedToDoc() {
        return mPrefs.getBoolean(PREF_Is_Connected_To_Doc, false);
    }

    public int getTimerCallCount() {
        return mPrefs.getInt(PREF_Progress_Call_Count, 0);
    }

    public void setTimerCallCount(int count) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(PREF_Progress_Call_Count, count);
        editor.apply();
    }

    public long getLastTimerTime() {
        return mPrefs.getLong(PREF_LAST_TIMER_TIME, 0);
    }

    public void setLastTimerTime(long time) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putLong(PREF_LAST_TIMER_TIME, time);
        editor.apply();
    }

    public int getLastTimerProgress() {
        return mPrefs.getInt(PREF_LAST_TIMER_PROGRESS, 0);
    }

    public void setLastTimerProgress(int progress) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(PREF_LAST_TIMER_PROGRESS, progress);
        editor.apply();
    }

    public int getLastCompletedConsultationId() {
        return mPrefs.getInt(PREF_LAST_COMPLETED_CONSULTATION_ID, -1);
    }

    public void setLastCompletedConsultationId(int consultationId) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(PREF_LAST_COMPLETED_CONSULTATION_ID, consultationId);
        editor.apply();
    }

    public String getSelectedCollectionType() {
        return mPrefs.getString(PREF_SELECTED_COLLECTION_TYPE, "");
    }

    public void setSelectedCollectionType(String collectionType) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SELECTED_COLLECTION_TYPE, collectionType);
        mEditor.commit();
    }

    public void clearSelectedCollectionType() {
        mPrefs.edit().remove(PREF_SELECTED_COLLECTION_TYPE);
    }

    public String getSelectedPackageSubType() {
        return mPrefs.getString(PREF_SELECTED_PACKAGE_SUB_TYPE, "");
    }

    public void setSelectedPackageSubType(String packageSubType) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SELECTED_PACKAGE_SUB_TYPE, packageSubType);
        mEditor.commit();
    }

    public void clearSelectedPackageType() {
        mPrefs.edit().remove(PREF_SELECTED_PACKAGE_SUB_TYPE);
    }

    public boolean isCowinRegistered() {
        return mPrefs.getBoolean(PREF_COWIN_REGISTERED, false);
    }

    public void setCowinRegistered(boolean isChecked) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_COWIN_REGISTERED, isChecked);
        mEditor.commit();
    }


    public int getLocationPermissionDeniedCount() {
        return mPrefs.getInt(LOC_PERMISSION_DENIED_COUNT, 0);
    }

    public void setLocationPermissionDeniedCount(int count) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(LOC_PERMISSION_DENIED_COUNT, count);
        editor.apply();
    }

    public String getTrackedEvents() {
        return mPrefs.getString(KEY_TRACKED_EVENTS, "");
    }

    public void setTrackedEvents(String trackedEventsJson) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_TRACKED_EVENTS, trackedEventsJson);
        editor.apply();
    }

    public void clearTrackedEvents() {
        setTrackedEvents("");
    }

}
