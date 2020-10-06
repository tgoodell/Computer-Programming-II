import javax.sound.midi.*;

public class Program
{
	public static void play(int[] notes, int[] durations) throws Exception
	{	
		try
		{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			Sequence sequence=new Sequence(Sequence.PPQ,16);
			Track t=sequence.createTrack();
			int time=0;
			for(int i=0;i<notes.length;i++)
			{
				if(notes[i]>0)t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, notes[i], 127),time));
				time+=durations[i];
				if(notes[i]>0)t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, notes[i], 127),time));
			}
			sequencer.setTempoInBPM(60);
			sequencer.setSequence(sequence);
			sequencer.start();
		}
		
		catch(MidiUnavailableException e)
		{
			e.printStackTrace();
			System.out.println("Midi Unavailable on this system.");
			return;
		}
		catch(InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.out.println("Invalid Midi Data.");
			return;
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		int[] notes={0,64,62,60,62,64,64,64,62,62,62,64,67,67,64,62,60,62,64,64,64,64,62,62,64,62,60};
		int[] durations={0,4,4,4,4,4,4,8,4,4,8,4,4,8,4,4,4,4,4,4,4,4,4,4,4,4,16};
		play(notes,durations);
		
	}
}
