package com.ac.documentdata.exception;

public class DocDataNotFoundException extends RuntimeException {
  public DocDataNotFoundException(String docId) {
    super("Doc Data Not Found for " + docId);
  }
}
