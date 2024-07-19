package dev.vaibhav.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
 //we cannot have this annotation do any work, so we create RunRepository class
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;  // can only use with @Repository on rr class
    }

    @GetMapping("")
   List<Run> findAll() {
       return runRepository.findAll();
   }
   @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
       Optional<Run> run = runRepository.findById(id);
       if (run.isEmpty()){
           throw new RunNotFoundException();
       }
       return run.get();
   }
   //post

   @PostMapping("")
   void create(@Valid @RequestBody Run run){  // to tell that run is coming from request body
        runRepository.create(run);
   }
   //put
   @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run,id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(id);
    }
    List<Run> findByLocation(@RequestParam String location) {
        return runRepository.findByLocation(location);
    }
}

