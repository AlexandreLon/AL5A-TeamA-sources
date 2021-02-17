package api

class MaintenanceService {

  def createMaintenance(maintenanceName: String, maintenanceType: TaskType, desiredDate: Date): Maintenance = {
    val maintenance: MaintenanceCreation = new MaintenanceCreation
    maintenance.setName(maintenanceName)
    maintenance.setType(maintenanceType)
    maintenance.setDesiredDate(desiredDate)
    val headers: HttpHeaders = new HttpHeaders
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
    headers.setContentType(MediaType.APPLICATION_JSON)
    val restTemplate: RestTemplate = new RestTemplate
    val requestBody: HttpEntity[Maintenance] = new HttpEntity[Maintenance](maintenance, headers)
    // Send request with POST method.
    return restTemplate.postForObject(String.format("http://%s:%s/api/maintenance", api.getHost, api.getPort), requestBody, classOf[Maintenance])
  }

}
