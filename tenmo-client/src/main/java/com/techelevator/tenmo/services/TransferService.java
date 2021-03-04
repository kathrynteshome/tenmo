package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.Transfer;


public class TransferService {
	
	private static String AUTH_TOKEN = ""; 
	private String BASE_URL;
	private RestTemplate restTemplate= new RestTemplate();

	public TransferService(String url) {
		this.BASE_URL = url;
	}
	
	
	public List<Transfer> viewTransfers(long id) throws TransferServiceException {
		List<Transfer> transfers = null;
		try { 
			transfers = restTemplate.exchange(BASE_URL + "transfers/id", makeAuthEntity(), Transfer.class).getBody();
			
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfers;
	}
	
	public Transfer viewTransferById(int transferId) throws TransferServiceException {
		Transfer transfer = null;
		try { 
			transfer = restTemplate.exchange(BASE_URL + 
					"transfers/" + transferId, HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transfer;
	}
	
	
	boolean sendBucks(int accountFrom, int accountTo, BigDecimal amount) throws TransferServiceException {
		//check that account balance is great than transfer amount
		
	}
	
    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }

    /**
     * Returns an {HttpEntity} with the `Authorization: Bearer:` header
     *
     * @return {HttpEntity}
     */
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
	
}
