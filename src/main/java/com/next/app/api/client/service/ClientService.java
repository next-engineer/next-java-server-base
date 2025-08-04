package com.next.app.api.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.next.app.api.client.repository.ClientRepository;
import com.next.app.api.client.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public Optional<Client> getClientByEmail(String contactEmail) {
        return clientRepository.findByContactEmail(contactEmail);
    }

    public Client createClient(Client client) {
        if (clientRepository.existsByContactEmail(client.getContactEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다: " + client.getContactEmail());
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client userDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다: " + id));

        client.setName(userDetails.getName());

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
