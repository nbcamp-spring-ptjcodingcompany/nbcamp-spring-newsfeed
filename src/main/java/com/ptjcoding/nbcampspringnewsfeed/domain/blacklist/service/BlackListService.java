package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto.BlackListRequestDto;

public interface BlackListService {

  void register(BlackListRequestDto dto);

  void unregister(BlackListRequestDto dto);
}
