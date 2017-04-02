package courseSelectionSystem;

import java.awt.Font;

/**
 *	This is the Chinese language pack of the software.
 */
class LanguageManager {
	public static final String[] DAYS = {"周日","周一","周二","周三","周四","周五","周六"};
	public static final String COMMA = "、";
	public static final String OK = "确定";
	public static final String CANCEL = "取消";
	public static final String NA = "不可用";
	public static final String UPLOADPHOTO = "上传照片";
	public static final String PHOTOFILES = "图片文件";
	public static final String WELCOME = "欢迎你";
	public static final String POSSIBLEOPERATION = "当前可选操作";
	public static final String OPERATION = "操作";
	public static final String ACCOUNTLABELCANNOTBEEMPTY = "账号栏不能为空";
	public static final String CHANGEPHOTO = "更换照片";
	public static final String RETURN = "返回";
	public static final String PASSWORDLABELCANNOTBEEMPTY = "密码栏不能为空";
	public static final String ACCOUNTORPASSWORDWRONG = "账号或密码错误";
	public static final String SERVERFILE = "服务器文件";
	public static final String CHOOSEASERVER = "选择服务器";
	public static final String DOMESTICSERVER = "本地服务器";
	public static final String SERVERNOTFOUND = "服务器错误,请选择服务器";
	public static final String ERROR = "错误";
	public static final String ONLINESERVER = "网络服务器";
	public static final String LOGIN = "登录";
	public static final String ACCOUNT = "账号";
	public static final String LOGINTOSYSTEM = "登录选课系统";
	public static final String PASSWORD = "密码";
	public static final String SYSTEMTITLE = "选课系统";
	public static final String PHOTO = "照片";
	public static final String NAME = "姓名";
	public static final String GENDER = "性别";
	public static final String TITLE = "身份";
	public static final String ID = "编号";
	public static final String VIEWPERSONALINFO = "查看个人信息";
	public static final String LOGOUT = "退出当前账户";
	public static final String COLLEGE = "院系";
	public static final String ADMIN = "教务";
	public static final String COURSEREQUIRED = "先修课程";
	public static final String COURSETITLE = "课程名";
	public static final String COURSEFLAG = "课程性质";
	public static final String COURSECOLLEGE = "开课院系";
	public static final String COURSEPROFESSOR = "授课教师";
	public static final String COURSETIME = "上课时间";
	public static final String CLASSROOM = "教室";
	public static final String CREDIT = "学分";
	public static final String STUDENTSENROLLEDANDLIMIT = "已选/限选";
	public static final String COURSEID = "课程号";
	public static final String ENROLL = "选课";
	public static final String COURSEDESCRIPTION = "课程简介";
	public static final String COURSESTATUS = "课程状态";
	public static final String COURSEENROLLED = "已选上";
	public static final String COURSEPASSED = "已通过";
	public static final String COURSEFAILED = "未通过";
	public static final String COURSEWAITING = "预选中";
	public static final String COURSEUNENROLLED = "未选上";
	public static final String COURSESCORE = "成绩";
	public static final String COURSEQUITTED = "已退课";
	public static final String CHANGEPASSWORD = "修改密码";
	public static final String OLDPASSWORD = "输入旧密码";
	public static final String NEWPASSWORD = "输入新密码";
	public static final String CONFIRMPASSWORD = "确认新密码";
	public static final String PASSWORDSTRENGTH = "密码强度";
	public static final String OLDPASSWORDWRONG = "旧密码错误";
	public static final String WRONGNEWPASSWORDLENGTH = "新密码长度需为6~16字节";
	public static final String CONFIRMPASSWORDNOTMATCH = "两次新密码输入不一致";
	public static final String CHANGEPASSWORDSUCCESSFUL = "密码修改成功";
	public static final String ENROLLEDCOURSES = "已选上的课程";
	public static final String ENROLLEDCREDIT = "已选学分：";
	public static final String COURSESELECTION = "补退选";
	public static final String VIEWCOURSEDESCRIPTION = "查看课程信息";
	
	public static Font getFont(int arg1, int arg2) {
		return new Font("宋体",arg1,arg2);
	}
}