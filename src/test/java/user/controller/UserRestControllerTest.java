package user.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import user.exception.UserNotFoundException;
import user.model.User;
import user.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserRestControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class UserRestControllerTest {

	/** The port. */
	@LocalServerPort
	private int port;

	/** The rest templete. */
	@Autowired
	TestRestTemplate restTemplete;

	/** The mock mvc. */

	private MockMvc mockMvc;

	/** The user service. */
	@Mock
	private UserService userService;

	/** The user rest controller. */
	@InjectMocks
	UserRestController userRestController;

	/** The Constant APPLICATION_JSON_UTF8. */
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	ObjectMapper objectMapper;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
	}

	/**
	 * Find all users test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void findAllUsersTest() throws Exception {

		User user1 = new User(1L, "Sam", 25, 1000);
		User user2 = new User(2L, "Tom", 30, 5000);
		User user3 = new User(3L, "Jerome", 25, 1000);
		User user4 = new User(4L, "Silvia", 35, 10000);
		when(userService.findAllUsers()).thenReturn(Arrays.asList(user1, user2, user3, user4));

		mockMvc.perform(get("/user/")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Sam")))
				.andExpect(jsonPath("$[0].age", is(25))).andExpect(jsonPath("$[0].salary", is(1000.0)))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Tom")))
				.andExpect(jsonPath("$[1].age", is(30))).andExpect(jsonPath("$[1].salary", is(5000.0)));

		verify(userService, times(1)).findAllUsers();
		verifyNoMoreInteractions(userService);

	}

	@Test
	public void findById_UserEntryNotFoundTest() throws Exception {

		mockMvc.perform(get("/user/{id}", 5L)).andExpect(status().isNotFound());

		verify(userService, times(1)).findById(5L);
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void add_NewUserEntryTest() throws Exception {

		User user5 = new User(5L, "Suresh", 35, 7000.0);

		mockMvc.perform(
				post("/user/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(user5)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
		;
	}

}
