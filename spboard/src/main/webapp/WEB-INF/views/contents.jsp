<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
         <!-- listbox -->
            <div class="listbox">
               <h3 class="mt-5"><i class="ri-arrow-right-double-line"></i> ${contents.title }</h3> 
               <div class="mt-2 mb-5 pt-2 border-top text-right">
                  <span class="mr-4"><label class="font-italic">hit:</label> ${contents.hit }</span>
                  <span class="mr-4 font-weight-bold">${contents.writer }</span>
                  <span class="mr-2"><fmt:formatDate value="${contents.wdate }" pattern="yyyy.MM.dd" /></span>
               </div>
               <!-- 
               <div class="mt-2 pt-2 border-top file-box">
                  <span>
                         <label class="font-italic">file :</label>
                         <a href="#">asdf.gif</a>  <a href="#">afdfd.asdf</a>
                  </span>
               </div>
               -->
               <div class="content-box mt-3">
                  ${contents.content }
               </div>
               
               <div class="my-5 pt-5 text-right">
                  <a href="list?cpg=${cpg }" class="btn btn-primary mr-3">목록</a>
                  <a href="rewrite?id=&refid=&depth=&renum=&cpg=" class="btn btn-primary">답글쓰기</a>
                  <a href="pass?id=&mode=edit" class="btn btn-primary">수정</a>
                  <a href="pass?id=&mode=del" class="btn btn-danger">삭제</a>                      
               </div>
               
            </div>