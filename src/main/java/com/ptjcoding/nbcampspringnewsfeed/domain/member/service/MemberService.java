package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

  MemberResponseDto signup(SignupRequestDto signupRequestDto);

  void login(LoginRequestDto loginRequestDto, HttpServletResponse response);

  void logout(HttpServletRequest request);

  void delete(Long memberId);
}
