package com.Ash.book_library_management.RestController;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.Ash.book_library_management.entity_DTO.MemberDTO;
import com.Ash.book_library_management.restControllers.MemberRestController;
import com.Ash.book_library_management.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MemberRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberRestController memberRestController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberRestController).build();
    }

    @Test
    void shouldReturnMemberById() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(1L);
        memberDTO.setFirstName("John");
        memberDTO.setLastName("Doe");

        when(memberService.getMemberById(1L)).thenReturn(memberDTO);

        mockMvc.perform(get("/api/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }
}
