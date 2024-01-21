package com.postechlances.producao.infra.mapper.impl;

import com.postechlances.producao.infra.mapper.IGenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenericMapper implements IGenericMapper {

  private final ModelMapper modelMapper;

  @Override
  public <T> T toObject(Object obj, Class<T> classType) {
    if(Objects.isNull(obj)) return null;
    return modelMapper.map(obj, classType);
  }

  @Override
  public <T> List<T> toList(List<?> list, Class<T> classType) {
    if(Objects.isNull(list) || list.isEmpty()) return Collections.emptyList();
    return list.stream().map(obj -> toObject(obj, classType)).collect(Collectors.toList());
  }

}
