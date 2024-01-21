package com.postechlances.producao.infra.mapper.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
  @NonNull
  private String status;
  //private Status status;
  private String message;
  private T data;
  private Long totalElements;
  private Integer totalPages;
  @Singular("item")
  private List<T> list;
  @Singular("error")
  private Map<Object, Object> errors;
}
