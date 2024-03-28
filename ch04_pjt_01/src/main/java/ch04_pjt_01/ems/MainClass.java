package ch04_pjt_01.ems;

import org.springframework.context.support.GenericXmlApplicationContext;

import ch04_pjt_01.ems.member.Student;
import ch04_pjt_01.ems.member.service.PrintStudentInformationService;
import ch04_pjt_01.ems.member.service.StudentModifyService;
import ch04_pjt_01.ems.member.service.StudentRegisterService;
import ch04_pjt_01.ems.member.service.StudentSelectService;
import ch04_pjt_01.ems.utils.InitSampleData;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
	             new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		//샘플데이터
		InitSampleData initSampleData = ctx.getBean("initSampleData" , InitSampleData.class);
		
		String[] sNums = initSampleData.getsNums();
	      String[] sIds = initSampleData.getsIds();
	      String[] sPws = initSampleData.getsPws();
	      String[] sNames = initSampleData.getsNames();
	      int[] sAges = initSampleData.getsAges();
	      char[] sGenders = initSampleData.getsGenders();
	      String[] sMajors = initSampleData.getsMajors();
	      
	   // 데이터베이스에 샘플 데이터 등록
	      StudentRegisterService registerService = 
	            ctx.getBean("studentRegisterService", 
	                  StudentRegisterService.class);
	      for (int i = 0; i < sNums.length; i++) {
	         registerService
	               .register(new Student(sNums[i], sIds[i], 
	                     sPws[i], sNames[i], sAges[i], 
	                     sGenders[i], sMajors[i]));
	      }
	      PrintStudentInformationService psi = 
	      ctx.getBean("printStudentInformationService",PrintStudentInformationService.class);
	      psi.printStudentInfo();//학생리스트를 전부 출력
	      
	      //학생 등록
	      registerService= ctx.getBean("studentRegisterService", StudentRegisterService.class);
	      registerService.register(new Student("hbs006", "dear","p0006","melissa",26,'W',"Music"));
	      psi.printStudentInfo();
	      
	      //특정 학번에 해당하는 학생 한명만 검색하고 출력
	      StudentSelectService studentSelectService = 
	    		  ctx.getBean("studentSelectService",StudentSelectService.class);
	      
	      Student selectedstudent = 
	    		  studentSelectService.select("hbs006");

	      System.out.println("STUDENT START ------------------");
	      System.out.print("sNum:" + selectedstudent.getsNum() + "\t");
	      System.out.print("|sId:" + selectedstudent.getsId() + "\t");
	      System.out.print("|sPw:" + selectedstudent.getsPw() + "\t");
	      System.out.print("|sName:" + selectedstudent.getsName() + "\t");
	      System.out.print("|sAge:" + selectedstudent.getsAge() + "\t");
	      System.out.print("|sGender:" + selectedstudent.getsGender() + "\t");
	      System.out.println("|sMajor:" + selectedstudent.getsMajor());
	      System.out.println("END ----------------------------");
	      
	      //특정 학번에 해당하는 학생의 정보를 수정하고 출력
	      
	      StudentModifyService ModifyService =  
	      ctx.getBean("studentModifyService",StudentModifyService.class);
	      
	}
	

}
