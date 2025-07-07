package com.perfulandia.perfulandia.assemblers;

import com.perfulandia.perfulandia.controller.PerfumeControllerV2;
import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Component
public class PerfumeModelAssembler implements RepresentationModelAssembler<Perfume, EntityModel<Perfume>> {
    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public EntityModel<Perfume> toModel(Perfume perfume) {
        return EntityModel.of(perfume,
                linkTo(methodOn(PerfumeControllerV2.class).getByIdPerfumeHATEOAS(perfume.getId())).withSelfRel(),
                linkTo(methodOn(PerfumeControllerV2.class).getAllPerfumesHATEOASv2()).withRel("perfumes"));
    }


}
