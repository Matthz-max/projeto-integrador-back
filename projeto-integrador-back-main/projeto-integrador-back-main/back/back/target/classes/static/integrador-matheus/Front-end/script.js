const modal = document.querySelector(".modal-container");
const tbody = document.querySelector("tbody");
const sModelo = document.querySelector("#m-modelo");
const sAno = document.querySelector("#m-ano");
const sPreco = document.querySelector("#m-preco");
const sCor = document.querySelector("#m-cor");
const sPlaca = document.querySelector("#m-placa");
const btnSalvar = document.querySelector("#btnSalvar");
const modalConfirm = document.querySelector("#modalConfirm");
const btnConfirmDelete = document.querySelector("#btnConfirmDelete");
const btnCancelDelete = document.querySelector("#btnCancelDelete");

let itens = [];
let modelo;
let id = null; // Usado para armazenar o id do carro editado

function openModal(edit = false, index = 0) {
  modal.classList.add("active");

  modal.onclick = (e) => {
    if (e.target.className.indexOf("modal-container") !== -1) {
      modal.classList.remove("active");
    }
  };

  if (edit) {
    sModelo.value = itens[index].modelo;
    sAno.value = itens[index].ano;
    sPreco.value = itens[index].preco;
    sCor.value = itens[index].cor;
    sPlaca.value = itens[index].placa;
    id = itens[index].id; // Armazenando o id do item para edição
  } else {
    sModelo.value = "";
    sAno.value = "";
    sPreco.value = "";
    sCor.value = "";
    sPlaca.value = "";
    id = null; // Resetando o id para novo item
  }
}

function editItem(index) {
  openModal(true, index);
}

function showConfirmDeleteModal(index) {
  itemToDelete = index;
  modalConfirm.classList.add("active");
}

btnConfirmDelete.onclick = () => {
  if (itemToDelete !== null) {
    deleteItem(itemToDelete);
    itemToDelete = null;
  }
  modalConfirm.classList.remove("active");
};

btnCancelDelete.onclick = () => {
  itemToDelete = null;
  modalConfirm.classList.remove("active");
};

btnSalvar.onclick = (e) => {
  e.preventDefault();
  if (sModelo.value === "" || sAno.value === "" || sPreco.value === "" || sCor.value === "" || sPlaca.value === "") {
    return;
  }

  const carImage = document.getElementById('m-img').files[0];

  if (!carImage) {
    alert("Por favor, carregue uma imagem.");
    return;
  }

  const reader = new FileReader();
  reader.onload = function (e) {
    const carImageData = e.target.result;

    const novoCarro = {
      modelo: sModelo.value,
      ano: sAno.value,
      preco: sPreco.value,
      cor: sCor.value,
      placa: sPlaca.value,
      imagem: carImageData
    };

    if (id !== null) { // Editar um carro existente
      fetch(`/carro/atualizar/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(novoCarro)
      })
        .then(response => response.json())
        .then(() => {
          loadItens(); // Atualiza a lista de carros
          modal.classList.remove("active");
        })
        .catch(error => console.error('Erro ao atualizar o carro:', error));
    } else { // Criar um novo carro
      fetch('/carro/criar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(novoCarro)
      })
        .then(response => response.json())
        .then(() => {
          loadItens(); // Atualiza a lista de carros
          modal.classList.remove("active");
        })
        .catch(error => console.error('Erro ao salvar o carro:', error));
    }
  };

  reader.readAsDataURL(carImage);
};

function loadItens() {
  fetch('/carro/listar')
  method: 'GET',
    .then(response => response.json())
    .then(data => {
      itens = data;
      tbody.innerHTML = "";
      itens.forEach((item, index) => {
        insertItem(item, index);
      });
    })
    .catch(error => console.error('Erro ao carregar os carros:', error));
}

function insertItem(item, index) {
  let tr = document.createElement("tr");
  tr.innerHTML = `
    <td><img src="${item.imagem}" alt="Imagem do Carro" style="max-width: 100px; max-height: 100px;" /></td>
    <td>${item.modelo}</td>
    <td>${item.ano}</td>
    <td>${item.preco}</td>
    <td>${item.cor}</td>
    <td>${item.placa}</td>
    <td class="acao">
      <button onclick="editItem(${index})"><i class='bx bx-edit'></i></button>
    </td>
    <td class="acao">
      <button onclick="showConfirmDeleteModal(${index})"><i class='bx bx-trash'></i></button>
    </td>
  `;
  tbody.appendChild(tr);
}

function deleteItem(index) {
  const idToDelete = itens[index].id;

  fetch(`delete{id}`, {
    method: 'DELETE',
  })
    .then(response => {
      if (response.ok) {
        loadItens();
        modalConfirm.classList.remove("active");
      }
    })
    .catch(error => console.error('Erro ao excluir o carro:', error));
}

loadItens();
