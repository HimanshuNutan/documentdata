package com.ac.documentdata.service;

import java.util.List;

import com.ac.documentdata.dao.DocumentDataDAO;
import com.ac.documentdata.exception.DocDataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ac.documentdata.model.DocumentData;

@Service
@Slf4j
public class DocumentDataService {

  @Autowired
  DocumentDataDAO documentDataDAO;

  public DocumentData findDocument(String docId) {
    return
            documentDataDAO
                    .findById(docId)
                    .orElseThrow(() -> new DocDataNotFoundException(docId));
  }

  public List<DocumentData> getAllDocuments() {

    return
            documentDataDAO
                    .findAll();
  }

  public void addDocument(DocumentData documentData) {
    DocumentData data = documentDataDAO.save(documentData);
    log.info("Document added/updated - {}", data);
  }

  public void updateDocument(DocumentData documentData) {
    if (documentData.hasId()) {
      addDocument(documentData);
    } else {
      throwDocumentNotExistException(documentData);
    }
  }

  private void throwDocumentNotExistException(DocumentData documentData) {
    log.warn("Document {} doesn't exist and so can't be updated");
    throw new RuntimeException("Document " + documentData + " doesn't exist and so can't be updated");
  }

  public void deleteDocument(String docName) {
    documentDataDAO.deleteById(docName);
  }

}
