-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29/05/2025 às 00:39
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `mv_motors`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpf` char(11) NOT NULL,
  `contato` varchar(100) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `cep` char(8) DEFAULT NULL,
  `rg` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`cpf`, `contato`, `cidade`, `estado`, `bairro`, `numero`, `rua`, `cep`, `rg`) VALUES
('11223344556', 'joao.santos@email.com', 'Belo Horizontes', 'MG', 'Savassi', 789, 'Av. Contorno', '30110000', 'MG1122334'),
('12345678901', 'teste@email.com', 'São Paulo', 'SP', 'Centro', 123, 'Rua Exemplo', '01001000', 'MG1234567'),
('22334455667', 'ana.gomes@email.com', 'Porto Alegre', 'RS', 'Moinhos de Vento', 101, 'Rua Padre Chagas', '90570000', 'RS2233445'),
('543543', 'gdgdf', 'dgfgdf', 'RJ', 'gdfgdf', 5454, 'gdfg', 'gdfg', 'gfdgdf'),
('98765432109', 'maria.silva@email.com', 'Rio de Janeiro', 'RJ', 'Tijuca', 456, 'Rua das Flores', '20550000', 'RJ9876543');

-- --------------------------------------------------------

--
-- Estrutura para tabela `compraveiculoloja`
--

CREATE TABLE `compraveiculoloja` (
  `idCompra` int(11) AUTO_INCREMENT PRIMARY KEY,
  `docCompraVenda` varchar(50) DEFAULT NULL,
  `dataCompra` date NOT NULL,
  `dataEntrega` date DEFAULT NULL,
  `fkVeiculos` int(11) NOT NULL,
  `fkProprietario` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `financiamento`
--

CREATE TABLE `financiamento` (
  `idFinanciamento` int(11) AUTO_INCREMENT PRIMARY KEY,
  `valor` decimal(18,2) NOT NULL,
  `parcelas` int(11) NOT NULL,
  `nomeBanco` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `financiamento`
--

INSERT INTO `financiamento` (`idFinanciamento`, `valor`, `parcelas`, `nomeBanco`) VALUES
(1, 70000.00, 60, 'Banco AXYZ'),
(2, 50000.00, 48, 'Banco Brasilia'),
(3, 90000.00, 72, 'Financia Mais'),
(4, 65000.00, 36, 'Crédito Fácil');

-- --------------------------------------------------------

--
-- Estrutura para tabela `modelos`
--

CREATE TABLE `modelos` (
  `IdModelo` int(11) AUTO_INCREMENT PRIMARY KEY,
  `marca` varchar(50) NOT NULL,
  `motor` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cilindradas` int(11) NOT NULL,
  `transmissao` char(10) NOT NULL,
  `capacidadeDoTanque` decimal(5,2) NOT NULL,
  `consumoMedio` decimal(5,2) NOT NULL,
  `potenciaMotor` decimal(5,2) NOT NULL,
  `arCondicionado` bit(1) NOT NULL,
  `airbagDianteiro` bit(1) NOT NULL,
  `airbagTraseiro` bit(1) NOT NULL,
  `direcaoHidraulica` bit(1) NOT NULL,
  `vidroEletrico` bit(1) NOT NULL,
  `camerasLaterais` bit(1) NOT NULL,
  `combustivel` char(10) NOT NULL,
  `cameraTraseira` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `modelos`
--

INSERT INTO `modelos` (`IdModelo`, `marca`, `motor`, `nome`, `cilindradas`, `transmissao`, `capacidadeDoTanque`, `consumoMedio`, `potenciaMotor`, `arCondicionado`, `airbagDianteiro`, `airbagTraseiro`, `direcaoHidraulica`, `vidroEletrico`, `camerasLaterais`, `combustivel`, `cameraTraseira`) VALUES
(1, 'Volkswagen', '1.0 TSI', 'Virtus Comfortline', 999, 'Automática', 52.00, 12.50, 128.00, b'1', b'1', b'0', b'1', b'1', b'0', 'Gasolina', b'1'),
(2, 'Chevrolet', '1.0 Turbo', 'Onix Plus LTZ', 999, 'Manual', 44.00, 13.00, 116.00, b'1', b'1', b'0', b'1', b'1', b'0', 'Flex', b'0'),
(3, 'Fiat', '1.3 Firefly', 'Cronos Drive', 1332, 'Manual', 48.00, 11.00, 107.00, b'1', b'1', b'0', b'1', b'1', b'0', 'Flex', b'0'),
(4, 'Toyota', '1.8 Flex', 'Corolla XEi', 1798, 'Automática', 50.00, 10.50, 139.00, b'1', b'1', b'1', b'1', b'1', b'1', 'Flex', b'1'),
(5, 'Honda', '1.5 VTEC', 'Civic EXL', 1498, 'Automática', 53.00, 12.00, 173.00, b'1', b'1', b'1', b'1', b'1', b'1', 'Gasolina', b'1'),
(112, 'asassss', '12123', '123123', 1212, '123123', 121.00, 1.00, 2.00, b'1', b'1', b'0', b'0', b'1', b'1', '213123', b'0');

-- --------------------------------------------------------

--
-- Estrutura para tabela `presta`
--

CREATE TABLE `presta` (
  `fkIdPrestador` int(11) NOT NULL,
  `fkIdTipoServico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `prestador`
--

CREATE TABLE `prestador` (
  `idPrestador` int(11) AUTO_INCREMENT PRIMARY KEY,
  `nome` varchar(100) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` char(8) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `contato` varchar(50) DEFAULT NULL,
  `tipoPrestador` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `servicoprestado`
--

CREATE TABLE `servicoprestado` (
  `idServico` int(11) AUTO_INCREMENT PRIMARY KEY,
  `valor` decimal(18,2) NOT NULL,
  `dataInicio` date NOT NULL,
  `dataFim` date DEFAULT NULL,
  `fkIdPrestador` int(11) NOT NULL,
  `fkIdVeiculo` int(11) NOT NULL,
  `fkIdTipoServico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `tiposervico`
--

CREATE TABLE `tiposervico` (
  `idTipoServico` int(11) AUTO_INCREMENT PRIMARY KEY,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `veiculoconcessionado`
--

CREATE TABLE `veiculoconcessionado` (
  `fkVeiculos` int(11) NOT NULL,
  `fkProprietario` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `veiculos`
--

CREATE TABLE `veiculos` (
  `IdVeiculo` int(11) AUTO_INCREMENT PRIMARY KEY,
  `IdModelo` int(11) NOT NULL,
  `complementos` varchar(100) DEFAULT NULL,
  `quilometragem` int(11) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `ano` int(11) NOT NULL,
  `documento` varchar(100) NOT NULL,
  `placa` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `veiculos`
--

INSERT INTO `veiculos` (`IdVeiculo`, `IdModelo`, `complementos`, `quilometragem`, `preco`, `ano`, `documento`, `placa`) VALUES
(101, 1, 'Cor metálica, insulfilm', 25000, 85000.00, 2022, 'DOC-VW-VIRTUS-2022-XYZ1234', NULL),
(102, 2, 'Kit multimídia, rodas de liga leve', 15000, 78000.00, 2023, 'DOC-CHEV-ONIX-2023-ABC5678', NULL),
(103, 3, 'Único dono, pintura sólida', 30000, 69000.00, 2021, 'DOC-FIAT-CRONOS-2021-DEF9012', NULL),
(104, 4, 'Teto solar, bancos de couro', 10000, 120000.00, 2023, 'DOC-TOY-COROLLA-2023-GHI3456', NULL),
(105, 5, 'Revisões em dia, ótimo estado', 40000, 110000.00, 2020, 'DOC-HONDA-CIVIC-2020-JKL7890', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

CREATE TABLE `venda` (
  `idVenda` int(11) AUTO_INCREMENT PRIMARY KEY,
  `docCompraVenda` varchar(50) DEFAULT NULL,
  `dataVenda` date NOT NULL,
  `dataRetirada` date DEFAULT NULL,
  `formaPagamento` varchar(50) DEFAULT NULL,
  `fkVeiculos` int(11) NOT NULL,
  `fkComprador` char(11) NOT NULL,
  `fkFinanciamento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `venda`
--

INSERT INTO `venda` (`idVenda`, `docCompraVenda`, `dataVenda`, `dataRetirada`, `formaPagamento`, `fkVeiculos`, `fkComprador`, `fkFinanciamento`) VALUES
(1, 'VENDA-001/2025', '2025-05-20', '2025-05-25', 'Financiamento', 101, '98765432109', 1),
(2, 'VENDA-002/2025', '2025-05-21', '2025-05-23', 'À Vista', 102, '11223344556', NULL),
(3, 'VENDA-003/2025', '2025-05-22', '2025-05-26', 'Financiamento', 104, '22334455667', 3);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`);

--
-- Índices de tabela `compraveiculoloja`
--
ALTER TABLE `compraveiculoloja`
  ADD KEY `fkVeiculos` (`fkVeiculos`),
  ADD KEY `fkProprietario` (`fkProprietario`);

--
-- Índices de tabela `financiamento`
--

--
-- Índices de tabela `modelos`
--

--
-- Índices de tabela `presta`
--
ALTER TABLE `presta`
  ADD PRIMARY KEY (`fkIdPrestador`,`fkIdTipoServico`),
  ADD KEY `fkIdTipoServico` (`fkIdTipoServico`);

--
-- Índices de tabela `prestador`
--

--
-- Índices de tabela `servicoprestado`
--
ALTER TABLE `servicoprestado`
  ADD KEY `fkIdPrestador` (`fkIdPrestador`),
  ADD KEY `fkIdVeiculo` (`fkIdVeiculo`),
  ADD KEY `fkIdTipoServico` (`fkIdTipoServico`);

--
-- Índices de tabela `tiposervico`
--

--
-- Índices de tabela `veiculoconcessionado`
--
ALTER TABLE `veiculoconcessionado`
  ADD KEY `fkVeiculos` (`fkVeiculos`),
  ADD KEY `fkProprietario` (`fkProprietario`);

--
-- Índices de tabela `veiculos`
--
ALTER TABLE `veiculos`
  ADD KEY `IdModelo` (`IdModelo`);

--
-- Índices de tabela `venda`
--
ALTER TABLE `venda`
  ADD KEY `fkVeiculos` (`fkVeiculos`),
  ADD KEY `fkComprador` (`fkComprador`),
  ADD KEY `fkFinanciamento` (`fkFinanciamento`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `compraveiculoloja`
--
ALTER TABLE `compraveiculoloja`
  ADD CONSTRAINT `compraveiculoloja_ibfk_1` FOREIGN KEY (`fkVeiculos`) REFERENCES `veiculos` (`IdVeiculo`),
  ADD CONSTRAINT `compraveiculoloja_ibfk_2` FOREIGN KEY (`fkProprietario`) REFERENCES `cliente` (`cpf`);

--
-- Restrições para tabelas `presta`
--
ALTER TABLE `presta`
  ADD CONSTRAINT `presta_ibfk_1` FOREIGN KEY (`fkIdPrestador`) REFERENCES `prestador` (`idPrestador`),
  ADD CONSTRAINT `presta_ibfk_2` FOREIGN KEY (`fkIdTipoServico`) REFERENCES `tiposervico` (`idTipoServico`);

--
-- Restrições para tabelas `servicoprestado`
--
ALTER TABLE `servicoprestado`
  ADD CONSTRAINT `servicoprestado_ibfk_1` FOREIGN KEY (`fkIdPrestador`) REFERENCES `prestador` (`idPrestador`),
  ADD CONSTRAINT `servicoprestado_ibfk_2` FOREIGN KEY (`fkIdVeiculo`) REFERENCES `veiculos` (`IdVeiculo`),
  ADD CONSTRAINT `servicoprestado_ibfk_3` FOREIGN KEY (`fkIdTipoServico`) REFERENCES `tiposervico` (`idTipoServico`);

--
-- Restrições para tabelas `veiculoconcessionado`
--
ALTER TABLE `veiculoconcessionado`
  ADD CONSTRAINT `veiculoconcessionado_ibfk_1` FOREIGN KEY (`fkVeiculos`) REFERENCES `veiculos` (`IdVeiculo`),
  ADD CONSTRAINT `veiculoconcessionado_ibfk_2` FOREIGN KEY (`fkProprietario`) REFERENCES `cliente` (`cpf`);

--
-- Restrições para tabelas `veiculos`
--
ALTER TABLE `veiculos`
  ADD CONSTRAINT `veiculos_ibfk_1` FOREIGN KEY (`IdModelo`) REFERENCES `modelos` (`IdModelo`);

--
-- Restrições para tabelas `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`fkVeiculos`) REFERENCES `veiculos` (`IdVeiculo`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`fkComprador`) REFERENCES `cliente` (`cpf`),
  ADD CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`fkFinanciamento`) REFERENCES `financiamento` (`idFinanciamento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
