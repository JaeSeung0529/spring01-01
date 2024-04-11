package ch05_pjt_01.contact.service;

import ch05_pjt_01.contact.ContactSet;
import ch05_pjt_01.contact.dao.ContactDao;

public class ContactSearchService {
	private ContactDao contactDao = null;

	public ContactSearchService() {

		System.out.println("ContactSearchService 의 default Constructor"); // 기본생성자
	}

   public ContactSearchService(ContactDao contactDao) {
       System.out.println(
    "ContactDao 파라미터 하나 가진 ContactSearchService 생성자");
      System.out.println(
       "ContactSearchService에서 contactDao: " + 
       contactDao);    // contactDao 출력
        this.contactDao = contactDao;
    }

	public ContactSet searchContact(String name) {
		if (verify(name)) {
			return contactDao.select(name);
		} else {
			System.out.println("Contact information is not available.");
		}

		return null;
	}

	public boolean verify(String name) {
		ContactSet contactSet = contactDao.select(name);
		return contactSet != null ? true : false;
	}

	public void setContactDao(ContactDao contactDao) {
		System.out.println("ContactSearchService의 setContactDao 메서드 호출됨.");
		this.contactDao = contactDao;
	}
}
