package tback.kicketingback.user.signup.dto.request;

import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
	@NotNull(message = "이름이 입력되지 않았습니다.")
	String name,
	@NotNull(message = "이메일이 입력되지 않았습니다.")
	String email,
	@NotNull(message = "비밀번호가 입력되지 않았습니다.")
	String password
) {
}
