package com.cocoe.spring.user.auth.jwt.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String COCOE_ORG = "Cocoe";
    public static final String USERMANAGEMENT = "User Management";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
   public static final String[] PUBLIC_URLS = {  "/seller/login", "/seller/register", 
		   "/admin/login", "/admin/register",
		   "/user/login", "/user/register",
		   "/user/login", "/user/register", "/user/image/**","/console/**" ,"/h2-console/**"};
    //public static final String[] PUBLIC_URLS = { "**" };
}
