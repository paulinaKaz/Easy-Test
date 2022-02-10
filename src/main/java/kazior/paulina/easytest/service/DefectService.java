package kazior.paulina.easytest.service;


import kazior.paulina.easytest.model.AppUser;
import kazior.paulina.easytest.model.Comment;
import kazior.paulina.easytest.model.Defect;
import kazior.paulina.easytest.model.TestCase;
import kazior.paulina.easytest.repository.DefectRepository;
import kazior.paulina.easytest.security.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DefectService {

    private final DefectRepository defectRepository;
    private final AppUserService appUserService;
    private final TestCaseService testCaseService;

    @Transactional(readOnly = true)
    public Collection<Defect> findAll() {
        return defectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Defect findByIdWithDetails(long id) {
        return defectRepository.findByIdWithDetails(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Defect assignUserToDefect(long defectId, long appUserId) {
        Defect defect = defectRepository.findByIdWithDetails(defectId).orElseThrow(EntityNotFoundException::new);
        AppUser appUser = appUserService.findById(appUserId);
        defect.setAssignUser(appUser);
        appUser.getDefects().add(defect);
        return defect;
    }

    @Transactional
    public Defect addNewDefect(Defect defect){
        AppUser appUser = appUserService.findSingleAppUserByUsername(SecurityContextUtil.getUserFromSecurityContext());
        appUser.getDefects().add(defect);
        defect.setAssignUser(appUser);
        return defect;
    }

    @Transactional
    public Defect assignTestCaseToDefect(long defectId, long testCaseId){
        Defect defect = defectRepository.findByIdWithDetails(defectId).orElseThrow(EntityNotFoundException::new);
        TestCase testCase = testCaseService.findById(testCaseId);
        defect.getTestCases().add(testCase);
        testCase.getDefects().add(defect);
        return defect;
    }

    @Transactional
    public Defect addCommentToDefect(long defectId, Comment comment){
        Defect defect = defectRepository.findByIdWithDetails(defectId).orElseThrow(EntityNotFoundException::new);
        defect.getComments().add(comment);
        comment.setDefect(defect);
        comment.setAddedBy(getUserFullName(appUserService.findSingleAppUserByUsername(SecurityContextUtil.getUserFromSecurityContext())));
        return defect;
    }

    @Transactional
    public void deleteDefect(long defectId){
        defectRepository.deleteById(defectId);
    }

    private String getUserFullName(AppUser appUser){
        return MessageFormat.format("{0} {1}",appUser.getFirstName(), appUser.getLastName());
    }



}
