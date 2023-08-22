package com.springboot.blog.service;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;

public interface AuthService {
    public String login(LoginDto loginDto);
    public String register(RegisterDto registerDto);
}
