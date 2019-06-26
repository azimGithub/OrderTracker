package com.example.azim.ordertracker.network;



import com.example.azim.ordertracker.model.OrderSubmitResponse;
import com.example.azim.ordertracker.model.appData.ResAppData;
import com.example.azim.ordertracker.model.login.PostLogin;
import com.example.azim.ordertracker.model.login.ResLogin;
import com.example.azim.ordertracker.model.postData.Orders;
import com.example.azim.ordertracker.model.postData.PostOrderData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rahil on 9/9/15.
 */
public interface ApiServices {



    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(ApiConstants.LOGIN)
    Call<ResLogin> apilogin(@Body PostLogin postRegister);


    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(ApiConstants.APP_DATA)
    Call<ResAppData> apiAppData();

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("mobapp/sync/frommobile")
    Call<OrderSubmitResponse> sortDealApi(@Body PostOrderData orders);



//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.LOGIN_ANKU)
//    Call<ResLogin> apiGetVerify(@Body PostLogin postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APISTANDARD)
//    Call<ResStandard> apiStandard(@Body PostStandard postData);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APISTUDENT)
//    Call<StudentList> apiGetStudents(@Body PostGettingList postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.apiSaveAttendance)
//    Call<ResAttendance> apiPostAttendance(@Body PostSaveAttendence postData);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APISUMMARYATTENDANCE)
//    Call<ResSummaryAttendance> apiGetStudentsSUmmary(@Body PostSummaryOnly postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APISUMMARYBYDATE)
//    Call<AttendanceByDatePojo> apiGetAttedanceByDate(@Body PostSummary postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIEXAM)
//    Call<ResExamData> apigetExam(@Body PostExam postExam);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIGETADDEXAM)
//    Call<ResAddExam> apiGetAddExamData(@Body PostExam postExam);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APISAVEEXAM)
//    Call<RestSaveExam> apiSaveAddExam(@Body PostAddExam postExam);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIGETADDEDITRESULT)
//    Call<ResAddEditResult> apiGetAddEditResult(@Body PostExam postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APISAVERESULT)
//    Call<RestSaveExam> apiSaveEditData(@Body PostEditAddResult postData);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIGETRESULTDATA)
//    Call<ResResultDetail> apiGetResultDetail(@Body PostExam postRegister);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIVIEWRESULT)
//    Call<ResViewResult> apiViewResul(@Body PostExam postExam);
//
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIPROFILE)
//    Call<ResTeacherProfile> apiGetProfile(@Body PostProfile postRegister);
//
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIGALLERYVIEW)
//    Call<ResGalleryView> apiGetGallery(@Body PostProfile postRegister);
//
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIEDITSAVEATTENDANCE)
//    Call<ResponseEditAttendance> apiEditSaveAttendance(@Body PostSaveAttendence postAttendance);
//
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIASSIGNMENTVIEW)
//    Call<ResAssignmentView> apiGetAssignmentView(@Body PostExam postRegister);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIEVENT)
//    Call<ResEvent> apiGetEvent(@Body PostProfile postRegister);
//
//
//    // Parent- Student Service
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIPARENTSTUDENT)
//    Call<ParentStudentsData> apiGetParentStudent(@Body PostExam postRegister);
//
//    // Parent- Student Service
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIPARENTSTUDENT_ATTENDANCE)
//    Call<ParentAttendanceData> apiGetParentAttendance(@Body PostData postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIPARENTEXAM)
//    Call<ParentExamData> apigetParentExam(@Body PostExam postExam);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIPARENTVIEWRESULT)
//    Call<ParentResultData> apiParentViewResul(@Body PostExam postExam);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.APIPARENTASSIGNMENTVIEW)
//    Call<AssignmentParent> apiParentAssignment(@Body PostExam postRegister);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.API_PARENT_GALLERYVIEW)
//    Call<ParentGalleryData> apiParentGallery(@Body PostProfile postRegister);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.API_PARENT_EVENT)
//    Call<ParentEventData> apiGetParentEvent(@Body PostProfile postRegister);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.API_PARENT_HOLIDAY)
//    Call<ParentHolidayData> apiGetHoliday(@Body PostProfile postRegister);
//
//    @Headers({"Accept:application/json","Content-type:application/json"})
//    @POST(ApiConstants.API_PARENT_PROFILE)
//    Call<ParentProfileData> apiGetParentProfile(@Body PostProfile postRegister);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIFIXEDROUTEBUS)
//    Call<ResRouteData> apiGetFixedRoute(@Path("transportId") String transportId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APICURRENTROUTE)
//    Call<ResCurrentRoute> apiGetCurrentRoute(@Path("transportId") String transportId, @Query("date") String date, @Query("routeType") String routeType);
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.APIPOSTFCM)
//    Call<ResRegister> apiPostFCM(@Body PostData postRegister, @Query("teacherId") String teacherId, @Query("parentId") String parentId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIPARETEACHERDRIVERS)
//    Call<ResPareTeachTransport> apiGetParTeachDriver(@Path("parentId") String parentId, @Path("studentId") String studentId);
//
//    @Multipart
//    @POST(ApiConstants.SAVETEACHERPROFILE)
//    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APISEARCHSTUDENT)
//    Call<ResSerach> apiSerachStudent(@Path("schoolId") String schoolId, @Query("prefix") String prefix);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_PTM_ATTENDANCE)
//    Call<ResAttendancePTM> apiGetAttendancePTM(@Path("standardId") String standardId, @Path("sectionId") String sectionId, @Path("studentId") String studentId);
//

//    @Multipart
//    @POST(ApiConstants.SAVE_ASIIGNMENT)
//    Call<ResEvent> apiPostAssignmentTeacher(@Part MultipartBody.Part image,
//                                            @Part("token") RequestBody token,
//                                            @Part("fk_schoolId") RequestBody fk_schoolId,
//                                            @Part("fk_standardId") RequestBody fk_standardId,
//                                            @Part("fk_sectionId") RequestBody fk_sectionId,
//                                            @Part("fk_subjectId") RequestBody fk_subjectId,
//                                            @Part("fk_teacherId") RequestBody fk_teacherId,
//                                            @Part("assign_type") RequestBody assign_type,
//                                            @Part("assign_title") RequestBody assign_title,
//                                            @Part("assign_desc") RequestBody assign_desc
//    );
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.GET_CURRENT_VER)
//    Call<ResForceUpdate> getCurrentVersion();
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APISEARCHSTUDENT)
//    Call<ResSerach> apiBusSelection(@Path("schoolId") String schoolId, @Query("prefix") String prefix);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIBUSATTENDANCESELECTION)
//    Call<ResBusAttenSelection> apiAttendanceBusSelection(@Path("teacherId") String teacherId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_ATTENDANCE_BUS_STUD_LIST)
//    Call<ResBusAttenToday> apiGetStudentBusATtendance(@Path("transportId") String transportId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_BUSATTENDANCE_PREVIOUS_DATE)
//    Call<ResHistoryBusAtten> apiGetBusAttPrviousDate(@Path("transportId") String transportId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.API_POST_BUS_ATTENDANCE)
//    Call<ResPostBusAtten> apiPostBusAttend(@Body PostBusAttendan postRegister);
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_BUSATTENDANCE_PREVIOUS_BY_DATE)
//    Call<ResHisDate> apiGetHistBusAttenDate(@Path("transportId") String transportId, @Path("date") String formattedDate);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_VIEWMULTI_SUBJECTS)
//    Call<ResMultiSubject> apiGetMultiSubjects(@Path("examId") String examId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_MULTIPLE_SUBJECT_SELECTION)
//    Call<ResSubjectSelection> apiGetSubjects(@Path("examId") String examId, @Path("teacherId") String teacherId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_CHECK_RESULT_STATUS)
//    Call<ResResultStatus> apiCheckResultStatus(@Path("examId") String examId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_GET_BUS_ATTEN_PARENT)
//    Call<ResBusAttenParentView> apiGetBusAttendance(@Path("studentId") String studentId, @Query("fromDate") String fromDate, @Query("endDate") String endDate);
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @PUT(ApiConstants.API_POST_BUS_EDIT_ATTEN)
//    Call<ResPostBusAtten> apiPostBusEditAtten(@Body ResPostSummaryData postDta);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIGETDROP_TEACHER)
//    Observable<ResGetDropPoint> apiGetDropsTeacher(@Path("teacherId") String teacherId);
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIGETDROP_TEACHER)
//    Call<ResGetDropPoint>apiGetDropsTeacherCall(@Path("teacherId") String teacherId);
//
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIGET_TEAC_DROP_LOCATION)
//    Observable<ResGetTeaLocation> apiGetDropTeaLocation(@Path("dropId") String dropId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.APIGET_TEAC_DROP_LOCATION)
//    Call<ResGetTeaLocation> apiGetDropTeaLocationCall(@Path("dropId") String dropId);
//
//    //Observable<ResData>
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.API_POST_TEAC_LOCATION)
//    Observable<ResData> apiPostTeaLocation(@Body PostTeachLatLong postRegister, @Path("dropId") String dropId);
//
//    //
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.API_POST_TEAC_LOCATION)
//    Call<ResData> apiPostTeaLocationService(@Body PostTeachLatLong postRegister, @Path("dropId") String dropId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.API_MULTISUBJECT_SELECTION)
//    Call<ResSubjectSelection1> apiGetSubjectsSelection(@Body PostExam postExam);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_SUBJECT_ATTRIBUTES)
//    Call<ResSubjectAttribute> apigetSubjectAttribute(@Path("subjectId") String subjectId);
//
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_EVETNT_SPLASH)
//    Call<ResEventSplash> apiGetEventSpash(@Query("id") String id, @Query("type") String type);
//
//
////    @Headers({"Accept:application/json","Content-Type:application/json"})
////    @GET("topstories/v2/home.json")
////    Call<NewsModel> fetchNews();
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_FEES_LIST)
//    public Single<FeesListResponse> getFeesService(@Query("schoolId") String schoolId, @Query("studentId") String studentId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_FEES_DETAIL)
//    public Observable<FeesDetailResponse> getFeesDetail(@Path("schoolId") String schoolId, @Path("studentId") String studentId, @Query("months") int monthName);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_FEES_DETAIL)
//    public Call<FeesDetailResponse> getFeesDetail1(@Path("schoolId") String schoolId, @Path("studentId") String studentId, @Query("month") int monthName);
//
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_FEES_HISTORY)
//    Single<FeesHistoryRes> getFeesHistory(@Path("studentId") String studentId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_FEES_HISTORY)
//    Call<FeesHistoryRes> getFeesHistoryCall(@Path("studentId") String studentId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.API_PAYMENT_POST)
//    public Call<ResPayData> postPaymentInfo(@Body PostPaymentData postData);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @POST(ApiConstants.API_POST_PARENT_LEAVE)
//   public Call<ResData> postLeaveByParent(@Body PostLeaveParent postLeaveParent, @Path("studentId") String studentId);
//
//    @Headers({"Accept:application/json","Content-Type:application/json"})
//    @GET(ApiConstants.API_FEES_LIST)
//    public Call<FeesListResponse> getFeesServices(@Query("schoolId") String schoolId, @Query("studentId") String studentId);
}
