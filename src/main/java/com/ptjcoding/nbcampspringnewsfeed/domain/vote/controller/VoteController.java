package com.ptjcoding.nbcampspringnewsfeed.domain.vote.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.service.VoteService;
import com.ptjcoding.nbcampspringnewsfeed.global.enums.GlobalSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

  private final VoteService voteService;


  @PostMapping
  public ResponseEntity<CommonResponseDto<VoteResponseDto>> createVote(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody VoteCreateRequestDto requestDto
  ) {
    VoteResponseDto responseDto = voteService.createVote(member, requestDto);

    return CommonResponseDto.ok(GlobalSuccessCode.CREATE, responseDto);
  }

  @PutMapping("/{voteId}")
  public ResponseEntity<CommonResponseDto<VoteResponseDto>> updateVote(
      @RequestAttribute("member") Member member,
      @PathVariable Long voteId,
      @Validated @RequestBody VoteUpdateRequestDto requestDto
  ) {
    VoteResponseDto responseDto = voteService.updateVote(member, voteId, requestDto);

    return CommonResponseDto.ok(GlobalSuccessCode.UPDATE, responseDto);
  }

  @DeleteMapping("/{voteId}")
  public ResponseEntity<CommonResponseDto<Void>> deleteVote(
      @RequestAttribute("member") Member member,
      @PathVariable Long voteId,
      @RequestParam(value = "safe", required = false) Boolean isSafe
  ) {
    voteService.deleteVote(member, voteId, isSafe);

    return CommonResponseDto.ok(GlobalSuccessCode.DELETE, null);
  }

}
