package com.ac.documentdata.controller;

import java.util.List;

import com.ac.documentdata.model.DocumentData;
import com.ac.documentdata.service.DocumentDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docinfo")
@Slf4j
@ControllerAdvice
public class DocumentDataController {

  private DocumentDataService documentDataService;

  @Autowired
  public DocumentDataController(DocumentDataService documentDataService) {
    this.documentDataService = documentDataService;
  }

  @ApiOperation(
          value = "Get document data for a document",
          response = DocumentData.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved data"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @GetMapping(value = "/{docId}")
  @PreAuthorize("hasAuthority('PERMISSIONS_read1')")
  public DocumentData getDocument(@PathVariable("docId") String docId) {
    log.info("getDocument() called");

    return
            documentDataService
                    .findDocument(docId);
  }

  @ApiOperation(
          value = "Get document data for all documents",
          response = DocumentData.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved data"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @GetMapping(value = "/all")
  public List<DocumentData> getAllDocuments() {
    log.info("getAllDocuments() called");

    return
            documentDataService
                    .getAllDocuments();
  }

  @ApiOperation(
          value = "Update document data for a document",
          response = DocumentData.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully updated data"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @PutMapping
  public void updateDocument(@RequestBody DocumentData doc) {
    documentDataService.updateDocument(doc);
  }

  @ApiOperation(
          value = "Add document data for a document",
          response = DocumentData.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully added data"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @PostMapping
  public void addDocument(@RequestBody DocumentData documentData) {
    log.info("addDocument() called");

    documentDataService.addDocument(documentData);
  }

  @ApiOperation(
          value = "Delete document data for a document",
          response = DocumentData.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully deleted data"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @DeleteMapping(value = "/{docId}")
  public void deleteDocument(@PathVariable("docId") String docId) {
    log.info("deleteDocument() called");

    documentDataService.deleteDocument(docId);
  }

}
