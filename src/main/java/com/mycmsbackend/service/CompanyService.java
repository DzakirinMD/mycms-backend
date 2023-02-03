package com.mycmsbackend.service;//package com.mycmsbackend.service;
//
//import com.mycmsbackend.model.Company;
//import com.mycmsbackend.repository.CompanyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service("companyService")
//public class CompanyService {
//
//    public final CompanyRepository companyRepository;
//
//    @Autowired
//    public CompanyService(CompanyRepository companyRepository) {
//        this.companyRepository = companyRepository;
//    }
//
//    public List<Company> getAllCompany() {
//        return companyRepository.findAll();
//    }
//
//    public Optional<Company> getAllCompanyById(Long id) {
//        return companyRepository.findById(id);
//    }
//}
