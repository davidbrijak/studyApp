class TokenDto {
  token: string;
}

export class UserTokensDto {
  accessToken: TokenDto;
  refreshToken: TokenDto;
}
