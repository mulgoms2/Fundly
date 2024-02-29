window.onload = ()=>{
    const saveBtn = document.querySelector(".save");
    saveBtn.addEventListener("click", updateProjectInfo);
};


async function updateProjectInfo(){
    const longTitle = document.querySelector("#longTitle").value;

    const updateObj = {
        pj_id : "01"
    }

   const response = await fetch("/editor/info",{
        method: "PATCH",
       headers:{
           "Content-Type": "application/json",
       },
       body: JSON.stringify(updateObj),
    });

    console.log(response);
    const resultObj = await response.json();

    console.log(resultObj);
}