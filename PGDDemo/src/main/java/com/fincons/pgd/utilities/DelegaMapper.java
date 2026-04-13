package com.fincons.pgd.utilities;

import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.models.Delega;

import java.util.List;

public class DelegaMapper {
	
	public static DelegaDTO buildDelegaDto(Delega u) {
		return DelegaDTO.builder() 
				.id(u.getId())

				.build();
				
	}
	
	public static List<DelegaDTO> buildDelegaDto (List<Delega> lU){
		
		return lU.stream()
				.map(u -> buildDelegaDto(u))
				.toList();
	}

}
