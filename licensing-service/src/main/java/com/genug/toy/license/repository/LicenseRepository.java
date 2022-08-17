package com.genug.toy.license.repository;

import com.genug.toy.license.model.License;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository {
    public List<License> findByOrganizationId(String organizationId);
    public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
