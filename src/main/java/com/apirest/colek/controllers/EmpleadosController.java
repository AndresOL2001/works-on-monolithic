package com.apirest.colek.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apirest.colek.DTOs.Empleado.EmpleadoDTO;
import com.apirest.colek.entity.Cliente;
import com.apirest.colek.entity.Empleado;
import com.apirest.colek.services.Implementation.ClienteService;
import com.apirest.colek.services.Implementation.EmpleadoService;
import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.exceptions.ParseException;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = {"http://localhost:4200", "https://gestoriaventura.com","http://gestoriaventura.com","http://gestoriaventura.com/COLEK","https://gestoriaventura.com/COLEK"})
public class EmpleadosController {
  
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH); 
    final int NUMERO_COLUMNAS = 10;
    private final EmpleadoService empleadoService;
    private final ClienteService clienteService;
    Logger logger = LoggerFactory.getLogger(EmpleadosController.class);

    @Autowired
    public EmpleadosController(EmpleadoService empleadoService,ClienteService clienteService) {
        this.empleadoService = empleadoService;
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity<Object> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleado, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos e intente nuevamente", HttpStatus.BAD_REQUEST);
            
            Empleado empleadoEntity = new Empleado();     
            Cliente clienteAux = new Cliente();       
            try {
                 clienteAux = this.clienteService.findByNombre(empleado.getIdCliente()).get();            
            } catch (Exception e) {
                return new ResponseEntity<> ("Error: No existe ningún cliente con ese id "+e.getMessage(), HttpStatus.BAD_REQUEST);
            }


            try {
                empleadoEntity.setId(empleado.getId());
                empleadoEntity.setArea(empleado.getArea());
                empleadoEntity.setPuesto(empleado.getPuesto());
                empleadoEntity.setRegion(empleado.getRegion());
                empleadoEntity.setZona(empleado.getZona());
                empleadoEntity.setCedis(empleado.getCedis());
                empleadoEntity.setNombre(empleado.getNombre());
                empleadoEntity.setSocio(empleado.getSocio());
                empleadoEntity.setCelular(empleado.getCelular());
                empleadoEntity.setCorreo(empleado.getCorreo());
                empleadoEntity.setPuntos(empleado.getPuntos());
                empleadoEntity.setIdCliente(clienteAux);
            
                empleadoService.crearEmpleado(empleadoEntity);
            } catch (Exception e) {
                return new ResponseEntity<>("El empleado no puede ser vacio", HttpStatus.BAD_REQUEST);
            }
          
        return new ResponseEntity<>("El empleado se a creado correctamente", HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Object> obtenerEmpleadosAsync() {
        try {

        CompletableFuture<List<Empleado>> empleados = this.empleadoService.obtenerEmpleados();


        List<Empleado> empleadosDTO = empleados.get();

         return new ResponseEntity<> (empleadosDTO, HttpStatus.OK);

        } catch (Exception e) {

          logger.error("Error: "+e.getMessage());

          return new ResponseEntity<> ("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obtenerEmpleadoPorIdAsync(@PathVariable Long id) {
        try {

        CompletableFuture<Empleado> empleado = this.empleadoService.buscarEmpleadoPorId(id);

        Empleado empleadoDTO = empleado.get();

         return new ResponseEntity<> (empleadoDTO, HttpStatus.OK);

        } catch (Exception e) {

          logger.error("Error: "+e.getMessage());

          return new ResponseEntity<> ("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/xlsx")
    public ResponseEntity<?> importarEmpleadosXlsx(@RequestParam("file") MultipartFile file) throws InterruptedException, ExecutionException{
        Map<String, Object> response = new HashMap<>();
        ArrayList<Empleado> registrosInsertados = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        String fileName = file.getOriginalFilename();
       // EstadoModel estadoAux = new EstadoModel();
        Set<String> extensionesPermitidas = new HashSet<>();
        extensionesPermitidas.add("xlsx");
        //Random random = new Random();
        String fileExtension = FilenameUtils.getExtension(fileName);
        // System.out.println(fileExtension);
        if (extensionesPermitidas.contains(fileExtension)) {
            try {
                BufferedInputStream bfs = new BufferedInputStream(file.getInputStream());
             
                Workbook workbook = new XSSFWorkbook(bfs);

                Sheet firstSheet = workbook.getSheetAt(0);

                StreamingReader reader = StreamingReader.builder()
                     .rowCacheSize(1000)    // number of rows to keep in memory (defaults to 10)
                     .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                     .sheetIndex(0)        // index of sheet to use (defaults to 0)
                     .read(file.getInputStream());            // InputStream or File for XLSX file (required)
                     
                    /*  for (Row r : reader) {
                        for (Cell c : r) {
                          System.out.println(c.getStringCellValue());
                        }
                      }    */
                 for(Row row :reader){

                    if(row.getRowNum() != 0){
                    int cont = 0;
                    Empleado empleadoAux = new Empleado();

                    for(int cn=0; cn<=9; cn++) {
                        //cellIterator.next()                  

                        Cell nextCell = row.getCell(cn,MissingCellPolicy.CREATE_NULL_AS_BLANK);            
                      switch (nextCell.getColumnIndex()) {
                            
                                case 0:
                                Cliente clienteAux = new Cliente();
                                try {
                                    clienteAux = this.clienteService.findByNombre(nextCell.getStringCellValue()).get();
                                } catch (Exception e) {
                                    logger.error("Error: ", e.getMessage());
                                }
                                empleadoAux.setIdCliente(clienteAux);
                                    break;
                                case 1:

                                empleadoAux.setArea(nextCell.getStringCellValue());

                                    break;
                                case 2:
                                empleadoAux.setPuesto(nextCell.getStringCellValue());


                                    break;
                                case 3:

                                empleadoAux.setRegion(nextCell.getStringCellValue());


                                    break;
                                case 4:
                                empleadoAux.setZona(nextCell.getStringCellValue());

                                    break;
                                case 5:
                                empleadoAux.setCedis(formatter.formatCellValue(nextCell));

                                    break;
                                case 6:

                                empleadoAux.setNombre(nextCell.getStringCellValue());
                                    break;
                                case 7:
                                 empleadoAux.setSocio(nextCell.getStringCellValue());
                                    break;
                                case 8:
                                empleadoAux.setCelular(nextCell.getStringCellValue());
                                    break;

                                case 9:
                                empleadoAux.setCorreo(nextCell.getStringCellValue());


                                    break;
                                default:
                                System.out.println("Error");    
                                break;
                            }
                        

                            cont++;
                        
                        if (cont == NUMERO_COLUMNAS) {

                            empleadoAux.setPuntos(new BigDecimal(0));
                     
                            registrosInsertados.add(empleadoAux);
                            cont = 0;
                        }

                    }
                }
                }
                bfs.close();
                reader.close();

                try {
                    this.empleadoService.crearEmpleados(registrosInsertados);
                } catch (Exception e) {
                    logger.error("error al crear empleados", e.getMessage());
                }


            } catch (IOException e) {
                response.put("mensaje", e.getCause().getCause().getMessage().toString());
                return new ResponseEntity<Map<String, Object>>(response,
                        HttpStatus.BAD_REQUEST);
            }

        } else {
            response.put("mensaje", "Formato Invalido");
            return new ResponseEntity<Map<String, Object>>(response,
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Se han insertado: "+registrosInsertados.size() +" registros");
       // return ResponseEntity.ok(registrosInsertados); // your return
    } 

    @PostMapping("/csv")
    public ResponseEntity<?> guardarCargaCSV(@RequestParam("file") MultipartFile file)
            throws IOException, ParseException {
                
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"ISO-8859-1"));
        int contadorHeader = 0;
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        ArrayList<Empleado> registrosInsertados = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        String line ="";

        // System.out.println(fileExtension);
        try {
            if (fileExtension.equals("csv")) {

                while ((line = br.readLine()) != null) {
                    Empleado empleadoAux = new Empleado();

                    if (contadorHeader > 0) {
                        
                        String[] data = line.split(",");
                        if (data[0].length() > 1 && data[0] != null) {
                  
                            Cliente clienteAux = new Cliente();
                            try {
                                clienteAux = this.clienteService.findByNombre(data[0]).get();
                            } catch (Exception e) {
                                logger.error("Error: ", e.getMessage());
                            }
                            empleadoAux.setIdCliente(clienteAux);
                        }
                        if (data[1].length() > 1 && data[1] != null) {
                            empleadoAux.setArea(data[1]);
                        }
                        if (data[2].length() > 1 && data[2] != null) {

                            empleadoAux.setPuesto(data[2]);
                        }
                        if (data[3].length() > 1 && data[3] != null) {
                            empleadoAux.setRegion(data[3]);
                        }
                        if (data[4].length() > 1 && data[4] != null) {
                            empleadoAux.setZona(data[4]);
                        }
                        if (data[5].length() > 1 && data[5] != null) {
                            empleadoAux.setCedis(data[5]);
                        }
                        if (data[6].length() > 1 && data[6] != null) {
                            empleadoAux.setNombre(data[6]);
                        }
                        if (data[7].length() > 1 && data[7] != null) {
                            empleadoAux.setSocio(data[7]);
                        }
                        if (data[8].length() > 1 && data[8] != null) {
                            empleadoAux.setCelular(data[7]);

                        }
                        if (data[9].length() > 1 && data[9] != null) {
                            
                            empleadoAux.setCorreo(data[9]);
                        }
                        empleadoAux.setPuntos(new BigDecimal(0));

                        registrosInsertados.add(empleadoAux);

                    }
                    
                        contadorHeader++;

                    }
                
            } else {
                response.put("error", "formato no valido");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            this.empleadoService.crearEmpleados(registrosInsertados);
        } catch (Exception e) {
            logger.error("error al crear empleados", e.getMessage());
        }

        return ResponseEntity.ok("Se han insertado: "+registrosInsertados.size() +" registros");

    }
    
}
