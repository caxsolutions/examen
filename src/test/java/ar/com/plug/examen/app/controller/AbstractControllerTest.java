/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.controller;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.jeasy.random.EasyRandom;
import org.junit.BeforeClass;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.SneakyThrows;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
public abstract class AbstractControllerTest {

	protected static final EasyRandom RANDOM = new EasyRandom();

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	//@BeforeClass
	public AbstractControllerTest() throws Exception{
		initMocks(this);
		final FormattingConversionService conversionService = new DefaultFormattingConversionService();

		mockMvc = standaloneSetup(getTarget()).setConversionService(conversionService).build();
		
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.disable(WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.enable(DEFAULT_VIEW_INCLUSION);
	}
	
	protected abstract Object getTarget();

	/*@SneakyThrows
	protected <I> void perform(final MockHttpServletRequestBuilder requestBuilder, final I request, final ResultMatcher status) {
		performRequest(requestBuilder, request, status);
	}

	protected <I, O> O perform(final MockHttpServletRequestBuilder requestBuilder, final I request, final Class<O> aClass, final ResultMatcher status) throws Exception {
		final MvcResult result = performRequest(requestBuilder, request, status);
		return (aClass != null) ? objectMapper.readValue(result.getResponse().getContentAsString(), aClass) : null;
	}*/

	@SneakyThrows
	protected <I, O> O perform(final MockHttpServletRequestBuilder requestBuilder, final I request, final TypeReference<O> typeReference, final ResultMatcher status) {
		final MvcResult result = performRequest(requestBuilder, request, status);
		return (typeReference != null) ? objectMapper.readValue(result.getResponse().getContentAsString(), typeReference) : null;
	}

	private <I> MvcResult performRequest(final MockHttpServletRequestBuilder requestBuilder, final I request,final ResultMatcher status) throws Exception {
		final String json = objectMapper.writeValueAsString(request);

		return this.mockMvc.perform(requestBuilder.content(json).contentType(APPLICATION_JSON)).andExpect(status).andReturn();
	}
}
