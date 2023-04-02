package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.AdminManager;
import com.mush.bumblebee.domain.Admin;
import com.mush.bumblebee.util.PasswordEncryption;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@DisplayName("Admin login service tests")
class AdminServiceImplTest {
private AdminManager adminManager;
private AdminService adminService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        adminManager = mock(AdminManager.class);
        adminService = AdminServiceImpl.getAdminServiceInstance();
    }

    @Test
    public void testVerifyLoginWithValidCredentials() throws SQLException, ClassNotFoundException,  IOException {
        String email = "example@gmail.com";
        String password = "password";
        Admin admin = new Admin();
        admin.setAdminFirstName("John");

        when(adminManager.verifyLogin(email, PasswordEncryption.encryptPassword(password))).thenReturn(admin);
        Whitebox.setInternalState(adminService, "adminManager", adminManager);

        Admin result = adminService.verifyLogin(email, password);
        assertNotNull(result);
        assertEquals(admin.getAdminFirstName(), result.getAdminFirstName());
    }

    @Test
    public void testVerifyLoginWithInvalidEmail() throws SQLException, ClassNotFoundException, IOException {
        String email = "example.com";
        String password = "password";
        Admin result = adminService.verifyLogin(email, password);
        assertNull(result);
    }

    @Test
    public void testVerifyLoginWithNullEmail() throws SQLException, ClassNotFoundException, IOException {
        String password = "password";
        Admin result = adminService.verifyLogin(null, password);
        assertNull(result);
    }

    @Test
    public void testVerifyLoginWithNullPassword() throws SQLException, ClassNotFoundException, IOException {
        String email = "example@example.com";
        Admin result = adminService.verifyLogin(email, null);
        assertNull(result);
    }
}