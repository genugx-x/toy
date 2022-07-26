package com.genug.toy.license.controller;

import com.genug.toy.license.model.License;
import com.genug.toy.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<?> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId);
        license.add(
                linkTo(
                        methodOn(LicenseController.class)
                                .getLicense(organizationId, license.getLicenseId()))
                        .withSelfRel(),
                linkTo(
                        methodOn(LicenseController.class)
                                .createLicense(organizationId, license, null))
                        .withRel("createLicense"),
                linkTo(
                        methodOn(LicenseController.class)
                                .updateLicense(organizationId, license))
                        .withRel("updateLicense"),
                linkTo(
                        methodOn(LicenseController.class)
                                .deleteLisence(organizationId, license.getLicenseId()))
                        .withRel("deleteLicense")
        );
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<?> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @PutMapping
    public ResponseEntity<?> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<?> deleteLisence(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }

    @GetMapping("/{licenseId}/{clientType}")
    public ResponseEntity<?> getLicensesWithClient(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @PathVariable("clientType") String clientType) {
        return ResponseEntity.ok(
                licenseService.getLicense(licenseId, organizationId));
    }

}