#define ERROR_0xFFFF 0xFFFF
// O erro 0xFFFF é um código crítico que indica que a BIOS do sistema detectou uma falha grave na bateria.  
// Este código aparece quando o controlador de energia ou o circuito de gerenciamento da bateria identifica  
// que a bateria não está funcionando dentro dos parâmetros esperados, o que pode afetar diretamente a estabilidade  
// e a segurança do sistema.  
// As causas podem incluir, mas não se limitam a:  
// 1. **Degradação extrema da capacidade**: A bateria pode ter perdido grande parte de sua capacidade original,  
//    sendo incapaz de fornecer energia suficiente ao sistema, mesmo que o LED de carregamento indique presença de energia.  
// 2. **Falha na comunicação com o controlador da BIOS**: O circuito interno da bateria (Smart Battery) envia  
//    informações incorretas ou incompletas para a BIOS, fazendo com que o sistema interprete um estado crítico.  
// 3. **Sobrecarga ou tensão fora do limite**: A bateria pode estar fornecendo tensão acima ou abaixo do valor seguro,  
//    o que é detectado imediatamente pela BIOS para evitar danos ao hardware.  
// 4. **Curto-circuito interno ou defeito físico**: Problemas como células danificadas ou conexões internas soltas  
//    podem acionar esse erro.  
// 5. **Problemas no conector ou cabos**: Às vezes, a falha não é da bateria em si, mas no contato com a placa-mãe.  
// Quando o erro 0xFFFF é detectado, o sistema pode se recusar a iniciar ou desligar inesperadamente,  
// pois a BIOS impede o funcionamento normal para proteger o hardware e os dados.  
// Para resolução, recomenda-se:  
// - Desligar completamente o dispositivo e remover a bateria, se possível, verificando visualmente se há danos ou deformações.  
// - Reinstalar a bateria corretamente, garantindo que os conectores estejam limpos e bem encaixados.  
// - Substituir a bateria por uma nova caso o erro persista.  
// - Atualizar a BIOS para a versão mais recente, que pode conter correções para detecção de falhas de energia.  
// Ignorar este erro pode resultar em desligamentos inesperados, perda de dados, falha de inicialização ou danos adicionais  
// ao sistema. É altamente recomendado tratar o problema antes de continuar o uso do dispositivo.
