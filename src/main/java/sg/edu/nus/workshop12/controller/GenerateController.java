package sg.edu.nus.workshop12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.workshop12.exception.RandomNumberException;
import sg.edu.nus.workshop12.model.Generate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GenerateController {
        private Logger logger = LoggerFactory.getLogger(GenerateController.class);
        
        @GetMapping("/")
        public String showGenerateForm(Model model){
                Generate generate = new Generate();
                model.addAttribute("generate", generate);
                return "generate";
        }

        @PostMapping("/generate")
        public String generateNumbers(@ModelAttribute Generate generate,
                Model model){
                logger.info("From the form " + generate.getNumberVal());
                int numberRandomNumbers = generate.getNumberVal();
                if (numberRandomNumbers > 10){
                        //throw new RandomNumberException();
                        model.addAttribute("errorMessage", "OMG exceed 10 already !");
                        return "error";
                }
                String[] imgNumbers = {
                        "1.png", "2.png", "3.png", "4.png", "5.png",
                        "6.png", "7.png", "8.png", "9.png", "10.png"
                };
                List<String> selectedImg = new ArrayList<String>();
                Random randNum = new Random();
                Set<Integer> uniqueGeneratedRandNumSet = new LinkedHashSet<Integer>();
                while(uniqueGeneratedRandNumSet.size() < numberRandomNumbers){
                        Integer resultOfRandNumbers = 
                                randNum.nextInt(generate.getNumberVal() +1);
                        uniqueGeneratedRandNumSet.add(resultOfRandNumbers);
                }

                Iterator<Integer> it = uniqueGeneratedRandNumSet.iterator();
                Integer currentElem = null;
                while(it.hasNext()){
                        currentElem = it.next();
                        logger.info("currentElem > " + currentElem);
                        selectedImg.add(imgNumbers[currentElem.intValue()]);
                }
                model.addAttribute("randNumsResult", selectedImg.toArray());
                return "result";
        }
}
