package com.api.tools.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.tools.dto.ToolsDTO;
import com.api.tools.model.ToolsModel;
import com.api.tools.service.ToolsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tools")
public class ToolsController {

	@Autowired
	private ToolsService toolsService;

	@GetMapping
	public ResponseEntity<List<ToolsModel>> getAllToolsModel() {
		return ResponseEntity.status(HttpStatus.OK).body(toolsService.findAllTools());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> GetById(@PathVariable(value = "id") Long id) {
		Optional<ToolsModel> toolsModelOptional = toolsService.findByIdTools(id);
		if (!toolsModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tools not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(toolsModelOptional.get());
	}

	@PostMapping("/insert")
	public ResponseEntity<Object> RegisterTools(@RequestBody @Valid ToolsDTO toolsDto) {

		var toolsModel = new ToolsModel();
		BeanUtils.copyProperties(toolsDto, toolsModel);
		Optional<Boolean> tituloExistente = toolsService.titleExisting(toolsModel.getTitle());
		if (tituloExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title already exists");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(toolsService.save(toolsModel));

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateTools(@PathVariable(value = "id") Long id,
			@RequestBody @Valid ToolsDTO toolsDto) {
		Optional<ToolsModel> toolsModelOptional = toolsService.findByIdTools(id);
		if (!toolsModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tools not found!");
		}
		var toolsModel = new ToolsModel();
		BeanUtils.copyProperties(toolsDto, toolsModel);
		toolsModel.setId(toolsModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(toolsService.save(toolsModel));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteTools(@PathVariable(value = "id") Long id) {
		Optional<ToolsModel> toolsModelOptional = toolsService.findByIdTools(id);
		if (!toolsModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tools not found!");
		}
		toolsService.delete(toolsModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Tools deleted successfully!");
	}

}
