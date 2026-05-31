System.out.println("==================================================");
        System.out.println("[BOOT] VocalGuard Engine Active & Running Live...");
        System.out.println("[STATUS] Continuous Background Listening Engaged...");
        System.out.println("==================================================");

        // Professional SE Practice: Start microphone ONCE outside the loop
        captureService.startListening();

        while (true) {
            // Buffer read karne ke liye chota sa delay taake data load ho sake
            try { Thread.sleep(100); } catch (InterruptedException e) { }

            byte[] audioData = captureService.captureAudio();
            double currentFrequency = 0.0;

            if (audioData != null) {
                currentFrequency = fftService.calculateFrequency(audioData);
            }
            
            // Debug line ko tabhi print karein jab sach mein koi aawaz aaye (Noise threshold > 50Hz)
            if (currentFrequency > 50.0) {
                if (authService.authenticate(currentFrequency, myProfile)) {
                    System.out.println("\n[🔥 HOTWORD MATCHED]: Ji Khizar, I am listening!");
                    System.out.print("TUDO >>> ");
                    
                    String instruction = scanner.nextLine();

                    if (instruction.toLowerCase().trim().equals("shutdown") || 
                        instruction.toLowerCase().trim().equals("band ho jao")) {
                        System.out.println("[SHUTDOWN] VocalGuard Engine closing safely. Allah Hafiz!");
                        break;
                    }

                    processor.process(instruction);
                    System.out.println("\n[SYSTEM] Returning to standby background mode...");
                }
            }
            // Agar frequency 0 ya bohot kam hai, toh chup-chap loop chalta rahega, terminal ko ganda nahi karega!
        }

        // Clean up resources when loop breaks
        captureService.stopListening();
        scanner.close();