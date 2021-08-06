package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.lenient;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
  private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController controller;

  @Mock
  BindingResult bindingResult;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @Mock
  Model model;

  @BeforeEach
  void setUp(){
    lenient().when(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).thenAnswer(invocationOnMock -> {
      ArrayList<Owner> owners = new ArrayList<>();

      String name = invocationOnMock.getArgument(0);

      if(name.equals("%Buck%")){
        owners.add(new Owner(1L, "Joe", "Buck"));
        return owners;
      }
      throw new RuntimeException("Invalid Argument");
    });

  }

  @Test
  void processCreationFormHasErrors() {
    //given
    Owner owner = new Owner(1l, "Jim", "%Buck%");
    lenient().when(bindingResult.hasErrors()).thenReturn(true);

    //when
    String viewName = controller.processCreationForm(owner, bindingResult);

    //then
    assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
  }

  @Test
  void processFindFormWildcardString(){
    //given
    InOrder inOrder = inOrder(ownerService, model);
    Owner owner = new Owner(1l, "Joe", "Buck");
    controller.processFindForm(owner, bindingResult, model);
    inOrder.verify(ownerService).findAllByLastNameLike(anyString());
    inOrder.verify(model).addAttribute(anyString(), anyList());
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getAllValues().get(0));
  }

  @Test
  void processFindFormWildcardStringWithCaptorAnnotation(){
    //given
    Owner owner = new Owner(1l, "Joe", "Buck");
    controller.processFindForm(owner, bindingResult, model);
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getAllValues().get(0));
  }

  @Test
  void processCreationFormNoErrors() {
    //given
    Owner owner = new Owner(5l, "Jim", "Bob");
    lenient().when(bindingResult.hasErrors()).thenReturn(false);
    given(ownerService.save(any())).willReturn(owner);

    //when
    String viewName = controller.processCreationForm(owner, bindingResult);

    //then
    assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
  }
}
