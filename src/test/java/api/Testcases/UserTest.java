package api.Testcases;




import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
//import api.endpoints.userEndpoint;
import api.payLoad.user;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	user userPayload;
	//	public static Logger logger;

	@BeforeClass
	public void generateTestData()
	{
		faker = new Faker();
		userPayload = new user();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());


		//obtain logger

		//logger = LogManager.getLogger("RestAssuredAutomationFramework_test");
	}

	@Test(priority=1)
	public void testCreateUser()
	{
		Response response = userEndpoints.createUser(userPayload);
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(),200);
	
	}


	@Test(priority=2)
	public void testGetUserData()
	{
		Response response = userEndpoints.GetUser(this.userPayload.getUsername());
		System.out.println("Read User Data.");
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(),200);
		//log
		//	logger.info("Get User Data executed.");
	}

	@Test(priority=3)
	public void testUpdateUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		Response response = userEndpoints.UpdateUser(this.userPayload.getUsername(),userPayload);
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(),200);
		//Read User data to check if first name is updated 
		Response responsePostUpdate = userEndpoints.GetUser(this.userPayload.getUsername());
		System.out.println("After Update User Data.");
		responsePostUpdate.then().log().all();


	}

	@Test(priority=4)
	public void testDeleteUser()
	{

		Response response = userEndpoints.DeleteUser(this.userPayload.getUsername());
		System.out.println("Delete User Data.");
		//log response
		response.then().log().all();
		//validation
		Assert.assertEquals(response.getStatusCode(),200);
		


	}


















}
