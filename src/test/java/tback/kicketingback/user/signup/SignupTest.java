package tback.kicketingback.user.signup;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import tback.kicketingback.email.service.EmailAuthService;
import tback.kicketingback.global.repository.RedisRepository;
import tback.kicketingback.user.domain.User;
import tback.kicketingback.user.exception.exceptions.AuthInvalidPasswordException;
import tback.kicketingback.user.exception.exceptions.EmailAuthIncompleteException;
import tback.kicketingback.user.exception.exceptions.EmailDuplicatedException;
import tback.kicketingback.user.repository.FakeUserRepository;
import tback.kicketingback.user.signup.service.DefaultSignUpService;

@SpringBootTest
public class SignupTest {
	@Autowired
	private EmailAuthService emailAuthService;

	@Autowired
	@Qualifier("signupRedisRepository")
	private RedisRepository signupRedisRepository;

	private final ConcurrentHashMap<String, User> map = new ConcurrentHashMap();
	private FakeUserRepository fakeUserRepository;
	private DefaultSignUpService defaultSignUpService;

	private final String TEST_EMAIL = "test@test.com";

	@BeforeEach
	void initBefore() {
		map.clear();
		fakeUserRepository = new FakeUserRepository(map);
		defaultSignUpService = new DefaultSignUpService(fakeUserRepository, emailAuthService);
	}

	@Test
	@DisplayName("회원가입 정상 처리 후 디비에 유저가 존재한다.")
	public void 회원가입_정상_처리_디비() {
		String username = "john";
		String password = "1234abc!@";

		emailAuthService.saveCode(TEST_EMAIL, "access");
		defaultSignUpService.signUp(username, TEST_EMAIL, password);

		assertThat(fakeUserRepository.existsByEmail(TEST_EMAIL)).isTrue();
	}

	@Test
	@DisplayName("회원가입 정상 처리 후 회원가입 레디스에 인증 정보가 삭제된다.")
	public void 회원가입_정상_처리_레디스() {
		String username = "john";
		String password = "1234abc!@";

		emailAuthService.saveCode(TEST_EMAIL, "access");
		defaultSignUpService.signUp(username, TEST_EMAIL, password);

		assertThat(fakeUserRepository.existsByEmail(TEST_EMAIL)).isTrue();
		assertThat(signupRedisRepository.getValues(TEST_EMAIL).isEmpty()).isTrue();
	}

	@Test
	@DisplayName("유저 이메일 중복이면 예외가 발생한다.")
	public void 이메일_중복일_경우_예외() {
		map.put("test@test.com", User.of(TEST_EMAIL, "123456a!!", "beach"));
		String username = "john";
		String password = "1234abc!@";

		emailAuthService.saveCode(TEST_EMAIL, "access");

		assertThrows(EmailDuplicatedException.class, () -> defaultSignUpService.signUp(username, TEST_EMAIL, password));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "123test12", "test@@@@@", "123123123!", "1a@", "1a@12312!@#!@!#!@#@!#@!2!@!!zda",
		"1a@12312!@#!@!#!@#@!#@!2!@!!zda"})
	@DisplayName("비밀번호가 형식에 맞지 않는 경우")
	public void 비밀번호_형식_맞지않음(String password) {
		String username = "john";

		emailAuthService.saveCode(TEST_EMAIL, "access");

		assertThrows(AuthInvalidPasswordException.class,
			() -> defaultSignUpService.signUp(username, TEST_EMAIL, password));
	}

	@Test
	@DisplayName("이메일 인증을 시도하지 않은 경우")
	public void 회원가입_전_이메일_인증_안함() {
		String username = "john";
		String password = "1234abc!@";

		assertThrows(EmailAuthIncompleteException.class,
			() -> defaultSignUpService.signUp(username, TEST_EMAIL, password));
	}

	@Test
	@DisplayName("이메일 인증을 완료하지 않은 경우")
	public void 회원가입_전_이메일_인증_완료_안함() {
		String username = "john";
		String password = "1234abc!@";

		emailAuthService.saveCode(TEST_EMAIL, "123123");

		assertThrows(EmailAuthIncompleteException.class,
			() -> defaultSignUpService.signUp(username, TEST_EMAIL, password));
	}
}
