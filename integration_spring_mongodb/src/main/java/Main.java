import com.jvasquez.persistence.service.CountryPersistenceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jorgevasquezang on 8/22/16.
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CountryPersistenceService countryPersistenceService = (CountryPersistenceService) context.getBean("countryPersistenceService");

        countryPersistenceService.getDTOCollection().forEach( countryDTO -> System.out.println(countryDTO.getName()));

    }
}
