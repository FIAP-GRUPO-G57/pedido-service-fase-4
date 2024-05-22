package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers;



import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Cliente;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDataMapper {

	private final ModelMapper modelMapper;

	public ItemDataMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Item toDomain(ItemEntity data) {
		return modelMapper.map(data, Item.class);
	}

	public ItemEntity toData(Item item) {
		if (modelMapper.getTypeMap(Produto.class, Long.class) == null) {
			modelMapper.createTypeMap(Produto.class, Long.class)
					.setConverter(context -> {
						if (context.getSource() != null) {
							return context.getSource().getId();
						} else {
							return null; // or return a default value
						}
					});
		}
		if (modelMapper.getTypeMap(Cliente.class, Long.class) == null) {
			modelMapper.createTypeMap(Cliente.class, Long.class)
					.setConverter(context -> {
						if (context.getSource() != null) {
							return context.getSource().getId();
						} else {
							return null; // or return a default value
						}
					});
		}
		return modelMapper.map(item, ItemEntity.class);
	}

	public List<Item> toDomain(List<ItemEntity> datas) {
		return datas.stream().map(this::toDomain).toList();
	}

	public List<ItemEntity> toData(List<Item> items) {
		return items.stream().map(this::toData).toList();
	}
	
}
