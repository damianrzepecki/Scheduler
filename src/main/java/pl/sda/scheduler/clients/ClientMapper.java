package pl.sda.scheduler.clients;

import org.mapstruct.Mapper;


@Mapper
public interface ClientMapper {
    ClientDTO clientToClientDTO(Client client);
    Client clientDTOtoClient(ClientDTO clientDTO);
}
