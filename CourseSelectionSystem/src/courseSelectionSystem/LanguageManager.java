package courseSelectionSystem;

import java.awt.Font;

/**
 *	This is the Chinese language pack of the software.
 */
class LanguageManager {
	public static final String[] DAYS = {"����","��һ","�ܶ�","����","����","����","����"};
	public static final String COMMA = "��";
	public static final String OK = "ȷ��";
	public static final String CANCEL = "ȡ��";
	public static final String NA = "������";
	public static final String UPLOADPHOTO = "�ϴ���Ƭ";
	public static final String PHOTOFILES = "ͼƬ�ļ�";
	public static final String WELCOME = "��ӭ��";
	public static final String POSSIBLEOPERATION = "��ǰ��ѡ����";
	public static final String OPERATION = "����";
	public static final String ACCOUNTLABELCANNOTBEEMPTY = "�˺�������Ϊ��";
	public static final String CHANGEPHOTO = "������Ƭ";
	public static final String RETURN = "����";
	public static final String PASSWORDLABELCANNOTBEEMPTY = "����������Ϊ��";
	public static final String ACCOUNTORPASSWORDWRONG = "�˺Ż��������";
	public static final String SERVERFILE = "�������ļ�";
	public static final String CHOOSEASERVER = "ѡ�������";
	public static final String DOMESTICSERVER = "���ط�����";
	public static final String SERVERNOTFOUND = "����������,��ѡ�������";
	public static final String ERROR = "����";
	public static final String ONLINESERVER = "���������";
	public static final String LOGIN = "��¼";
	public static final String ACCOUNT = "�˺�";
	public static final String LOGINTOSYSTEM = "��¼ѡ��ϵͳ";
	public static final String PASSWORD = "����";
	public static final String SYSTEMTITLE = "ѡ��ϵͳ";
	public static final String PHOTO = "��Ƭ";
	public static final String NAME = "����";
	public static final String GENDER = "�Ա�";
	public static final String TITLE = "���";
	public static final String ID = "���";
	public static final String VIEWPERSONALINFO = "�鿴������Ϣ";
	public static final String LOGOUT = "�˳���ǰ�˻�";
	public static final String COLLEGE = "Ժϵ";
	public static final String ADMIN = "����";
	public static final String COURSEREQUIRED = "���޿γ�";
	public static final String COURSETITLE = "�γ���";
	public static final String COURSEFLAG = "�γ�����";
	public static final String COURSECOLLEGE = "����Ժϵ";
	public static final String COURSEPROFESSOR = "�ڿν�ʦ";
	public static final String COURSETIME = "�Ͽ�ʱ��";
	public static final String CLASSROOM = "����";
	public static final String CREDIT = "ѧ��";
	public static final String STUDENTSENROLLEDANDLIMIT = "��ѡ/��ѡ";
	public static final String COURSEID = "�γ̺�";
	public static final String ENROLL = "ѡ��";
	public static final String COURSEDESCRIPTION = "�γ̼��";
	public static final String COURSESTATUS = "�γ�״̬";
	public static final String COURSEENROLLED = "��ѡ��";
	public static final String COURSEPASSED = "��ͨ��";
	public static final String COURSEFAILED = "δͨ��";
	public static final String COURSEWAITING = "Ԥѡ��";
	public static final String COURSEUNENROLLED = "δѡ��";
	public static final String COURSESCORE = "�ɼ�";
	public static final String COURSEQUITTED = "���˿�";
	public static final String CHANGEPASSWORD = "�޸�����";
	public static final String OLDPASSWORD = "���������";
	public static final String NEWPASSWORD = "����������";
	public static final String CONFIRMPASSWORD = "ȷ��������";
	public static final String PASSWORDSTRENGTH = "����ǿ��";
	public static final String OLDPASSWORDWRONG = "���������";
	public static final String WRONGNEWPASSWORDLENGTH = "�����볤����Ϊ6~16�ֽ�";
	public static final String CONFIRMPASSWORDNOTMATCH = "�������������벻һ��";
	public static final String CHANGEPASSWORDSUCCESSFUL = "�����޸ĳɹ�";
	public static final String ENROLLEDCOURSES = "��ѡ�ϵĿγ�";
	public static final String ENROLLEDCREDIT = "��ѡѧ�֣�";
	public static final String COURSESELECTION = "����ѡ";
	public static final String VIEWCOURSEDESCRIPTION = "�鿴�γ���Ϣ";
	
	public static Font getFont(int arg1, int arg2) {
		return new Font("����",arg1,arg2);
	}
}