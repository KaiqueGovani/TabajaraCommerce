# TabajaraCommerce - Projeto de Sistema de Controle de Compras

### Descrição
Este projeto desenvolve um sistema de controle de compras para a empresa TabajaraCommerce. O sistema gerencia clientes, produtos e compras, oferecendo um conjunto abrangente de funcionalidades para facilitar a administração de vendas e estoque. Desenvolvido seguindo o padrão MVC (model-view-controller).

### Funcionalidades
O sistema inclui as seguintes funcionalidades:
1. **Cadastro de Clientes**: Permite o registro de clientes, tanto pessoa física quanto jurídica.
2. **Exclusão de Clientes**: Os clientes podem ser removidos pelo CPF, CNPJ ou pelo nome.
3. **Cadastro de Produtos**: Registra produtos, incluindo produtos perecíveis com data de validade.
4. **Realização de Compras**: Os clientes podem realizar compras que incluem vários itens.
5. **Atualização de Pagamentos**: Gerencia o status de pagamento das compras.
6. **Relatórios Diversos**: Inclui relatórios detalhados sobre clientes, produtos e compras.
7. **Encerramento do Sistema**: Opção para sair do sistema, salvando todas as alterações realizadas.

### Especificações Técnicas
- **Clientes**: Armazenam informações como nome, endereço, data de cadastro, CPF/CNPJ, e outros atributos específicos para pessoa física e jurídica.
- **Produtos**: Incluem dados como código, nome, descrição, preço e, para produtos perecíveis, data de validade.
- **Compras**: Registram detalhes da compra, incluindo itens, quantidade, preço e status de pagamento.
- **Persistência de Dados**: Utiliza arquivos de texto para armazenar dados de clientes, produtos e compras. Uma classe especializada é responsável pela leitura e gravação desses dados.

### Arquitetura
O sistema é construído com base em programação orientada a objetos em Java, seguindo princípios de encapsulamento, herança e polimorfismo. A interação com o usuário é realizada principalmente através da classe `JOptionPane` e classes de gerenciamento específicas para clientes, produtos e compras.

### Instruções para Execução
1. Inicie o sistema, e ele verificará automaticamente a existência de arquivos de texto na pasta `baseDados`.
2. Navegue pelo menu para acessar diferentes funcionalidades.
3. Ao sair, o sistema atualizará os arquivos de texto com as novas informações.

### Desenvolvimento e Colaboração
Este projeto é parte de uma avaliação acadêmica e foi desenvolvido em colaboração por:
- Kaique Medeiros Govani
- Milton Rogerio Dotto Penha Junior
- Joao Rafael Jordao Pereira

---

**Obs:** Esse texto foi escrito com auxilio de uma IA. Apesar de todos os esforços terem sido tomados para garantir a coerência dos fatos, podem haver incongruências nas informações.
