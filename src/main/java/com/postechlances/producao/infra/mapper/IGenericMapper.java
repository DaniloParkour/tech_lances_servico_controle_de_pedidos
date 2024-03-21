package com.postechlances.producao.infra.mapper;

import java.util.List;

public interface IGenericMapper {
  <T> T toObject(Object obj, Class<T> classType);
  <T> List<T> toList(List<?> list, Class<T> classType);
}
