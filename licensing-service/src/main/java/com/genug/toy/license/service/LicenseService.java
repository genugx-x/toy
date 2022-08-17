package com.genug.toy.license.service;

import com.genug.toy.license.config.ServiceConfig;
import com.genug.toy.license.model.License;
import com.genug.toy.license.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    @Autowired
    MessageSource messages;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;

    public License getLicense(String licenseId, String organizationId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (null == license)
            throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null), licenseId, organizationId));

        return license.withComment(config.getProperty());
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            // responseMessage = String.format("This is the post and the object is: %s", license.toString());
            responseMessage = String.format(
                    messages.getMessage("license.create.message", null, locale), // 컨트롤러에서 locale를 전달받은 경우
                    license.toString()
            );
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            // responseMessage = String.format("This is the put and the object is: %s", license.toString());
            responseMessage = String.format(
                    messages.getMessage("license.update.message", null, null),
                    license.toString()
            );
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
        return responseMessage;
    }
}
