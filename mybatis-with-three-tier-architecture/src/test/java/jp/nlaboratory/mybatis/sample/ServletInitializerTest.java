package jp.nlaboratory.mybatis.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
class ServletInitializerTest {

  @Mock
  private SpringApplicationBuilder springApplicationBuilder;

  @Test
  public void servletInitializer() {
    // Arrange
    ServletInitializer servletInitializer = new ServletInitializer();
    when(springApplicationBuilder.sources(MybatisSampleApplication.class)).thenReturn(springApplicationBuilder);

    // Act
    SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

    // Assert
    verify(springApplicationBuilder).sources(MybatisSampleApplication.class);
    assertEquals(springApplicationBuilder,result);
  }
}
